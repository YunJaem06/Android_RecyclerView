# [RecyclerView을 이용하여 클론 어플 제작]
***
#### Tech Stack
* Glide, navigation, Gson

***
### RecyclerView 뷰홀더

* 뷰를 보관하는 객체입니다.
* RecyclerView에 보이는 여러 개의 아이템은 내부에서캐시되기 때누에 아이템 개수만큼 객체로 만들어지지 않습니다.
* 메모리를 효율적으로 사용하려면 뷰홀더에 뷰 객체를 넣어두고 사용자가 스크롤하게 되고 화면에 보이지 않게 된 뷰 객체를 새로 보일 쪽에 재사용하는 것이 효율적이기 때문입니다.(이 과정에서 뷰홀더가 재사용 됩니다.)
* 이렇게 된다면 RecyclerView 체크가 풀리게 됩니다. 이유는 스크롤을 하면 재 생성하기 때문입니다.

* onCreateViewHolder() : RecyclerView는 뷰홀더를 새로 만들어야 할 때마다 이 메서드를 호출해야합니다. 이 메서드는 위에서 정의한 아이템 뷰 레이아웃을 이용해 뷰 객체를 만듭니다. 그리고 뷰 객체를 새로든 뷰홀더 객체에 담아 반환합니다. 만
* onBindViewHolder() : RecyclerView는 뷰홀더를 데이터와 연결할 때 이 메서드를 호출합니다.
* getItemCount(): 어댑터에서 관리하는 아이템의 개수를 반환합니다.

***
### Recycler View
<center class="half">
    <img src="https://user-images.githubusercontent.com/96619472/207537964-708fbab3-6d95-4113-ad82-63b3fc757e0d.png" width="300"/>
</center>

### checkBox 재사용 해결
<p align="center">
<img src="https://user-images.githubusercontent.com/96619472/207537239-39580a46-af4f-4809-83e1-a0178ddc8c77.mp4">
</p>

### CRUD(생성, 읽기, 수정, 삭제)
<p align="center">
<img src="https://user-images.githubusercontent.com/96619472/207537972-ad0e97b6-3750-4084-97ba-9323bf2e613e.png" width="20%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/207537975-63f680ba-dc48-4f4f-b80d-f951950862f8.png" width="20%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/207537980-accde388-0991-42dc-bcee-62b70c3c8ff4.png" width="20%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/207537982-90c06434-b29f-454f-a70b-4b582f962a8a.png" width="20%" height="30%">
</p>

### Drag and Drop
<center class="half">
    <img src="https://user-images.githubusercontent.com/96619472/207537984-ce2a184c-03d6-4eba-8e03-c89932d1fe3e.png" width="300"/>
</center>

***
### 화면을 만들면서 배운것
* RecyclerView
* 재사용에 대한 공부와 checkBox 오류 해결
* CRUD
* Drag and Drop

#### 공부해야할 것들
* 데이터 저장에 대한 이해와 활용방법
* SharedPreferences 관리 방법
