## 구현할 기능 목록

### ► 참여자 이름 입력 기능
참여자 이름은 쉼표(,)로 구분한다.<br>
[예외] 5글자 초과일 경우<br> 
[예외] 이름이 중복될 경우<br>

### ► 실행 결과 입력 기능
실행 결과는 쉼표(,)로 구분한다.<br>
[예외] 꽝 또는 자연수가 아닌 경우<br>
[예외] 실행 결과 개수가 참여자 수와 다른 경우<br>

### ► 사다리 최대 높이 입력 기능
[예외] 숫자가 아닐 경우<br>
[예외] 0 이하의 값이 들어올 경우

### ► 사다리 생성 기능
사다리의 가로 라인은 겹칠 수 없다.
  ```
  |-----|-----| // 불가
  |-----|     | // 가능
  ```

### ► 사다리 출력 기능
- 참여자 목록과 실행 결과 목록이 포함된 사다리를 출력한다.

### ► 사다리 타기 실행 기능

### ► 사다리 타기 결과 조회 기능
- 결과를 조회할 참여자 이름 입력 기능<br>
  [예외] 참여자 목록에 없는 이름인 경우(all 제외)<br>
  [예외] 공백 또는 null인 경우<br><br>

- 실행 결과 출력 기능
  - 개인별 실행 결과 출력 기능<br>
    참여자의 이름을 입력한 경우 해당 참여자의 실행 결과를 출력한다.
  - 전체 참여자 실행 결과 출력 기능<br>
    all을 입력한 경우 전체 참여자의 실행 결과를 출력한다.
