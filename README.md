# [LifeCycle을 이용하여 클론 어플 제작]
***
#### Tech Stack
* Glide, navigation

***
### Splash 화면
![Untitled](https://user-images.githubusercontent.com/96619472/203973934-34bb1610-d056-43b6-9aa6-47a32340fba0.png)

### Login 화면
![Untitled (1)](https://user-images.githubusercontent.com/96619472/203973937-470837ab-e9b0-46b2-9380-68bcea00c59b.png)

### 회원가입 화면
![Untitled (4)](https://user-images.githubusercontent.com/96619472/203973926-5665e1a4-0afd-4d9f-908c-6c7f52db19aa.png)
* onCreate() 처음 실행 되는 상태에서 먼저 호출되는 메소드 이기 때문에 스플래쉬 화면이나 로그인 화면등 Activity가 자동생성
* onStart() 메소드를 사용하여 한번 로그인을 하고 true 값이 저장되어 있으면 다음번에는 바로 mainActivity로 갈 수 있게 사용하였습니다.
* 자동로그인은 onStart를 사용하면서 onPause()를 이용하여 데이터를 임시로 저장하였고 onPause가 되면 auto가 true인 것을 확인하고 텍스트에 미리 작성하게 하였습니다.
* onRestart()는 화면이 비활성화 된다면 작성했던 텍스트를 초기화 하기위하여 사용하였습니다

### 홈화면
![Untitled (2)](https://user-images.githubusercontent.com/96619472/203973932-e86e33c1-cd71-454b-93e9-647fa816d878.png)
* 움직이는 이미지를 따로 누끼 딸수가 없어 대체 이미지로 적용하였습니다.

### 매장 선택화면
![5](https://user-images.githubusercontent.com/96619472/203973928-1ca0e713-2987-4565-80f7-c9063e62ff5f.png)
* tabLayout을 이용하여 구현하였습니다.

### 더보기 화면
![Untitled (3)](https://user-images.githubusercontent.com/96619472/203973931-a0cd84b3-94fa-4483-8e2d-0091fbbe81c5.png)

### 계정설정 화면
![Untitled (5)](https://user-images.githubusercontent.com/96619472/203973923-6a03d6a7-63b9-43e4-8174-4e19c024132c.png)
* onStop()을 사용하여 자동 로그인 값을 false로 바꾸고 다시 어플을 입장하면 다시 로그인을 할 수 있게 하기 위해서 구현하였습니다.
* onDestroy()을 사용하여 사용했던 자원들을 전부 제거

***
### 화면을 만들면서 배운것
* Glide를 활용하여 움직이는 이미지를 넣을수 있었습니다(https://yunjaem06.tistory.com/120)
* lifecycle를 이용하여 자동로그인를 활용하는 방법을 배웠습니다.
* tabLayout를 활용해 볼 수 있었던 계기가 되었습니다. (다음에는 다양하게 변경하면서 연습해봐야 겠습니다.)

#### 공부해야할 것들
* Bottom navigaion
* 데이터 전달에 대한 것들
* Jetpack Navigation Graph에 대해 공부
* tabLayout 활용에 대한 공부
