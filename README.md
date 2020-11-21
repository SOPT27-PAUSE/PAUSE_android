# ⏸ PAUSE_android ⏸
<img src="https://imgur.com/spjezf0.png" width="20%">

> ### 알고리즘의 늪에서 나를 구할, 영상 시청 시간 관리 앱. 
> ### 일상을 위한 멈춤, Pause.

![Pause](https://user-images.githubusercontent.com/63148508/99887976-328bd300-2c8c-11eb-976a-ef44bf29f19a.png)

<br>

## BASE URL
### http://sopkathon-pause.tk
👆🏻 클릭해 보세요!😀

## 💁‍♀️ 개발 담당 부분
* **조현진 - 메인화면 다이얼로그, 플레이리스트뷰**
  * 퍼즈 시작하기 클릭 시 다이얼로그 - 시작하기 클릭 - 플레이리스트뷰로 이동
  * 플레이리스트뷰에서 아이템 클릭 시 유튜브 재생목록 url로 이동, timer 시작
  
* **박효송 - 스플래시, 시청기록뷰**
  * 스플래시뷰 1초 구동
  * 시청기록뷰에서 막대그래프로 일주일치 시청기록 보여줌
  
* **권예진 - 로그인뷰, 메인뷰, 종료뷰**
  * 로그인 버튼 클릭 시 메인뷰로 이동
  * 하단 버튼 클릭 시 시청기록뷰로 이동

## 🖥 Interface
```kotlin
interface RequestInterface {

    // 플레이리스트 조회
    @Headers("Content-Type: application/json")
    @GET("/playlist")
    fun requestPlaylist(
        @Query("playtime") playtime : Int,
        @Query("category") category : String
    ) : Call<ResponseListData>

    // 로그인
    @Headers("Content-Type: application/json")
    @POST("/auth/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>

    // 이용시간 조회
    @Headers("Content-Type: application/json")
    @GET("/usage")
    fun returnTime(
        @Header("jwt") jwt : String
    ) : Call<ResponseRecData>

}
```
