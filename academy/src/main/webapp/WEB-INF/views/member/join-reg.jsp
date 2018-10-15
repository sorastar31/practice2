<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main>
	<!-- 아이디, 이름, 이메일, 비밀번호 : 생년월일, 전화번호, 닉네임, 성별 -->
	<section id="form-section">
		<h1>회원가입 페이지 2</h1>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>
						<label>사진 : </label>
						<img class="photo" src="">
						<input type="file" hidden="true" name="photo-file" value="사진"/>
						<span class="photo-button">사진선택</span>
					</td>
				</tr>
				<tr>
					<td>
						<label>아이디 : </label>  <!-- 유효성검사 : 4글자이상 영어로만 -->
						<input name="id" value="${uid}" required="required" />
						<input type="button" class="id-check-button" value="중복확인"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>비밀번호 : </label>
						<input name="pwd" type="password" required="required"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>이름 : </label>
						<input type="text" name="name" required="required"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>이메일 : </label>
						<input type="text" name="email" readonly="readonly" value="${email}" required="required"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>다음 계산 결과는?<img alt="계산식" name="calc" src="moonjae.jpg"></label>
						<input name="moonjae" type="text"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="회원가입"/>
					</td>
				</tr>
			</table>
		</form>
	</section>
</main>

<script type="text/javascript">
	window.addEventListener("load",function(event){
		var formSection = document.querySelector("#form-section");
		var idCheckBtn = formSection.querySelector(".id-check-button");
		var idInput = formSection.querySelector("input[name='id']");
		var joinBtn = formSection.querySelector("input[type='submit']");
		var photoBtn = formSection.querySelector(".photo-button");
		var fileBtn = formSection.querySelector("input[type='file']");
		var photo = formSection.querySelector(".photo");
		
		var idOk = false;

		
		fileBtn.onchange = function(e){
			var file = fileBtn.files[0];

			// for(var p in file)
			// 	alert(p);
			if(file.type.indexOf("image/") < 0){
				alert("이미지 형식이 아닙니다.");
				return;
			}
			if(file.size > 1024*1024*10){
				alert("10MB를 초과할 수 없습니다.");
				return;
			}

			var reader = new FileReader();

			reader.onload = function(evt){
				photo.src = evt.target.result;
			};
			reader.readAsDataURL(file);
		};
		
		
		
		photoBtn.onclick = function(e){
			var event = new MouseEvent("click",{
				'view':window,
				'bubbles':true,
				'cancelable':true
			});
			fileBtn.dispatchEvent(event);
		};
		
		/* photoBtn.addEventListener("click", function(){
			var event = new MouseEvent("click",{
					'view':window,
					'bubbles':true,
					'cancelable':true
			});
			var fileBtn = formSection.querySelector("input[type='file']");
			fileBtn.dispatchEvent(event);
		}); */

		joinBtn.onclick = function(e){
			//중복이 없다면	
			if(!idOk){
				alert("중복확인을 해주세여");
				e.priventDefault();
			}
		};

		idCheckBtn.onclick = function(e){
			//ajax --> 협력자 백엔드에게 연락해서 알아봐야 함
			// /member/is-id-duplicated
			// 요청
			var id = idInput.value;
			var request = new XMLHttpRequest();
		    request.onload = function(){
		    	var duplicated = JSON.parse(request.responseText);
				if(duplicated){
					alert("이미 있는 아이디에염");			
					return;
				}
				alert("사용가능함");
				idOk = true;
				
		    };
		    request.open("GET", "is-id-duplicated?id="+id, true);
		    request.send();
		};
		
	});
</script>