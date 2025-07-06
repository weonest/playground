package org.example.springai.analyzer.error

import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LineNumberNode
import org.objectweb.asm.tree.MethodNode
import org.springframework.asm.Type
import org.springframework.stereotype.Component

@Component
class StackTraceAnalyzer {
    fun analyze(stackTraceElements: List<StackTraceElement>): List<MethodSignatureInfo> {
        return stackTraceElements.mapNotNull { getMethodSignature(it.className, it.lineNumber) }
    }

    private fun getMethodSignature(className: String, lineNumber: Int): MethodSignatureInfo? {
        val classInputStream = this.javaClass.classLoader
            .getResourceAsStream(className.replace('.', '/') + ".class")
            ?: return null

        val classNode = ClassNode()
        val classReader = ClassReader(classInputStream)
        classReader.accept(classNode, 0)

        for (method in classNode.methods) {
            val lineNumbers = mutableListOf<Int>()

            for (instruction in method.instructions) {
                if (instruction is LineNumberNode) {
                    lineNumbers.add(instruction.line)
                }
            }

            if (lineNumber in lineNumbers) {
                return extractMethodSignature(className, lineNumber, method)
            }
        }
        return null
    }

    private fun extractMethodSignature(
        className: String,
        lineNumber: Int,
        method: MethodNode
    ): MethodSignatureInfo {
        val argumentTypes = Type.getArgumentTypes(method.desc)
        val localVariables = method.localVariables ?: listOf()
        // 인스턴스 메서드면 첫 번째(this) 변수는 제외
        val isStatic = (method.access and org.objectweb.asm.Opcodes.ACC_STATIC) != 0
        val parameterNames = localVariables
            .drop(if (isStatic) 0 else 1)
            .take(argumentTypes.size)
            .map { it.name }

        val parameters = argumentTypes.mapIndexed { index, type ->
            MethodSignatureInfo.ParameterInfo(
                name = parameterNames.getOrElse(index) { "arg$index" },
                type = type.className
            )
        }

        return MethodSignatureInfo(
            className = className,
            lineNumber = lineNumber,
            parameters = parameters,
            returnType = Type.getReturnType(method.desc).className
        )
    }
}
