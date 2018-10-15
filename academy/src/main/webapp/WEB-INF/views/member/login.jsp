<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script> -->
<main>
  	<section>
  		<h1>회원 로그인</h1>
  		<form method="post">
  			<table>
  				<tr>
  					<td>
  						<label>아이디 : </label>
  						<input name="username"/>
  					</td>
  				</tr>
  				<tr>
  					<td>
  						<label>비밀번호 : </label>
  						<input type="password" name="password"/>
  					</td>
  				</tr>
  				<tr>
  					<td>
  						<input type="submit" value="로그인"/>
  					</td>
  				</tr>
  			</table>
  		</form>
  		<a href="find-id">아이디 찾기</a>
  		<a href="reset-pwd">비밀번호 재설정</a>
	</section>
	<!-- 네이버아이디로로그인 버튼 노출 영역 -->
	<div id="naver_id_login"></div>
	<!-- //네이버아이디로로그인 버튼 노출 영역 -->
</main>
<!-- <script type="text/javascript">
	var naver_id_login = new naver_id_login("XMTHqpFScFytCftpQyVg", "http://localhost:8080/jaehwan/index");
	var state = naver_id_login.getUniqState();
	naver_id_login.setButton("white", 2,40);
	naver_id_login.setDomain("http://localhost:8080/jaehwan/member/login");
	naver_id_login.setState(state);
	naver_id_login.init_naver_id_login();
</script> -->