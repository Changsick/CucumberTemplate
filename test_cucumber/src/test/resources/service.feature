Feature: POST request to store and retrieve data

  Scenario: POST 데이터를 받아서 DB에 저장하고 저장된 데이터 리턴하기
    Given 데이터가 비어있는 상태
    When 클라이언트가 POST 요청으로 데이터를 전송하면
    Then 저장된 데이터가 DB에 존재하고 클라이언트에게 리턴된다
  
  Scenario: 전체 데이터 조회
    Given 데이터가 있는 상태
    When 클라이언트가 Get 요청으로 데이터를 전송하면
    Then 리턴받은 데이터를 확인한다.