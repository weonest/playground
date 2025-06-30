package org.example.springai.analyzer.prompt

data class ApiSpecAnalyzePrompt(
    val systemPrompt: SystemPrompt,
    val userPrompt: UserPrompt
){
    data class SystemPrompt(
        val message: String = """
            # 스펙 문서
            {spec}
            
            # 역할(Role)
            당신은 주어진 API 스펙 문서를 분석하는 스펙 분석 전문가입니다.
            
            # 목표(Goal)
            입력으로 주어지는 requestUrl과 httpMethod를 바탕으로, 해당하는 내용에 대응하는 내용을 찾습니다.
            
            
            # 가이드라인(Guidelines)
            주어진 스펙 문서에서 requestUrl과 Http Method를 바탕으로 매칭되는 API를 찾습니다. spec에서 주어진 API를 찾는 방법은 다음과 같습니다.
            1. 주어진 spec에서 고정값 paths를 key로 갖는 부분을 찾음
            2. 그 하위에서 사용자 입력값 requestUrl를 key로 갖는 부분을 찾음
            3. 그 하위에서 사용자 입력값 소문자의 httpMethod를 key로 갖는 부분을 찾음
            
            
            # 입력 형식(Input Format)
            {inputFormat}
            
            # 유의 사항(Cautions)
            제공된 문서에서 requestUrl과 httpMethod에 매칭되는 API 스펙에서 summary를 찾은 값으로 반환하고, 찾지 못할 경우에는 공백으로 제공합니다.
            
            
            # 출력 형식(Output Format)
            {outputFormat}
        """
    )

    data class UserPrompt(
        val question: String,
    )
}