다음은 Terraform의 기본 사용법과 심화 내용인 특정 리소스에 대해 `plan`/`apply`하는 법을 포함한 설명을 담은 `README.md` 형식의 마크다운 문서입니다

## Terrafrom
Terraform은 HashiCorp에서 개발한 Infrastructure as Code(IaC) 도구로, 클라우드 인프라를 선언적으로 구성하고 관리할 수 있도록 도와줍니다.
````bash
📦 설치

brew install terraform  # macOS
# 또는
choco install terraform # Windows
````

```bash
설치 확인

terraform -version
```

---

## 📁 프로젝트 초기화

```bash
terraform init
```

* provider 플러그인 다운로드
* `.terraform/` 폴더 생성

---

## 🔍 변경 사항 미리 보기

```bash
terraform plan
```

* 실제 리소스 변경 없이, 어떤 작업이 수행될지를 보여줍니다.

---

## 🚀 인프라 적용

```bash
terraform apply
```

* 계획(plan)을 실행하여 실제로 리소스를 생성/수정/삭제합니다.

---

## 🧹 인프라 삭제

```bash
terraform destroy
```

* 현재 상태에 존재하는 리소스 모두를 삭제합니다.

---

## 🗃️ 상태 파일

* Terraform은 리소스 상태를 `terraform.tfstate`에 저장합니다.
* 이 파일은 Git 등의 VCS에 포함하지 않도록 주의하고, 원격 백엔드(ex: S3) 사용을 권장합니다.

---

## 🎯 특정 리소스만 `plan` 또는 `apply` 하는 방법

Terraform은 디렉토리 내 모든 리소스를 기본적으로 대상으로 합니다. 하지만 다음 명령어를 사용해 **특정 리소스만 실행**할 수 있습니다.

### 특정 리소스만 plan

```bash
terraform plan -target=aws_iam_role.fluentbit_cloudwatch_role -target=local_file.fluentbit_sa_manifest                                        
```

### 특정 리소스만 apply

```bash
terraform apply -target=aws_instance.my_instance
```

> `-target`은 의존성 있는 리소스만 대상으로 하며 전체 구성을 무시하기 때문에 **일관성에 유의**해야 합니다.

---

## 🧠 기타 명령어

```bash
terraform validate         # 문법 검사
terraform fmt              # 코드 포맷 정리
terraform taint 리소스명   # 특정 리소스를 강제로 recreate 대상 지정
terraform graph            # 리소스 의존성 그래프 출력 (DOT 형식)
```

---

## 📚 참고 문서

* 공식 사이트: [https://www.terraform.io](https://www.terraform.io)
* 명령어 참고: [https://developer.hashicorp.com/terraform/cli](https://developer.hashicorp.com/terraform/cli)
* Provider 문서 (예: AWS): [https://registry.terraform.io/providers/hashicorp/aws/latest/docs](https://registry.terraform.io/providers/hashicorp/aws/latest/docs)
