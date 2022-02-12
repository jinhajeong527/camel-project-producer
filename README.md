# camel-project-producer
camel-producer-project
Quartz 라이브러리를 활용하여 두개의 라우트를 주기적으로 실행한다.
해당 프로젝트는 2개의 에이전트로 구동되며, 스케줄 실행 시 2개의 에이전트 중 하나의 에이전트만 실행된다.

1. aTypeSendRoute: 30초 주기로 실행되고, JSON 형태의 데이터 생성하여 data-a-jh 토픽으로 송신한다.
2. bTypeSendRoute: 1분 주기로 실행하며, XML 형태의 데이터를 생성하여 data-b-jh 토픽으로 송신한다.
3. historySendRoute: 이력 송신 라우트로, 위의 두 라우트를 실행 후 송신 이력 정보를 data-history 토픽으로 송신한다.
에이전트 아이디, 라우트 아이디, 송신시각, 송신성공여부 등의 정보를 담고 있다(JSON 형식)
