# RestTemplate

    RestTemplate란? HTTP 통신 기능을 손쉽게 하도록 설계된 템플릿입니다.
    AsyncRestTemplate를 사용하면 비동기 방식으로 HTTP 통신 기능을 손쉽게 사용할 수 있습니다.

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

# RestTemplate-UriComponentsBuilder

    UriComponentsBuilder = 여러 파라미터를 연결해서 URI 형식으로 만드는 기능을 제공
    - fromUriString
    - path
    - encode
    - expand
    - queryParam

    # 헤더 정보 설정
    RequestEntity<MemberDTO> requestEntity = RequestEntity
    .post(uri)
    .header("my-header", "wikibooks api")
    .body(memberDTO);

# RestTemplate-HttpClient

    RestTempate는 커넥션 풀 기능을 지원하지 않기 때문에
    HttpClient를 사용해서 커넥션 풀 기능을 활성화 한다.

    <dependency>
        <groupId>org.apache.httpcomponents.client5</groupId>
        <artifactId>httpclient5</artifactId>
        <version>5.1.3</version>
    </dependency>

    public RestTemplate restTemplate() {
        // 타임아웃 설정
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofDays(2000)) // 연결 타임아웃 (2초)
                .setResponseTimeout(Timeout.ofDays(5000)) // 응답 타임아웃 (5초)
                .build();

        // 커넥션 매니저 설정
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnTotal(500)
                .setMaxConnPerRoute(500)
                .build();

        // HttpClient 설정
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        // HttpComponentsClientHttpRequestFactory 생성
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }
    
    