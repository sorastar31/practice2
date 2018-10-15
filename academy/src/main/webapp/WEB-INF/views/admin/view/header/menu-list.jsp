<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
	<form method="post">
		<section>
			<h1>메뉴 목록</h1>
			<ul>
				<c:forEach var="mainMenus" items="${mainMenus}">
					<li>
						<input type="checkbox" name="menu-id" value="${mainMenus.id}" />
						<span>${mainMenus.title}</span>
						<input type="submit" name="action" value="수정" onclick="document.querySelector('.selected-menu-id').value = parentElement.firstElementChild.value;"/>
						<input type="submit" name="action" value="삭제" onclick="document.querySelector('.selected-menu-id').value = parentElement.firstElementChild.value;"/>
					</li>
				</c:forEach>
			</ul>
			<fieldset> 
				<legend>메뉴 추가 필드</legend>
				<ul>
					<li>
						<label>메뉴명 : </label>
						<input name="title" value="${selectedMenu.title}" />
					</li>
					<li>
						<label>url : </label>
						<input name="url" value="${selectedMenu.url}" />
					</li>
				</ul>
			</fieldset>
			<div>
				<input type="hidden" name="selected-menu-id" class="selected-menu-id" value="${selectedMenu.id}" />
				<input type="submit" name="action" value="일괄삭제" />
				<input type="submit" name="action" value="저장" />
				<input type="submit" name="action" value="추가" />
			</div>
		</section>
	</form>
</main>
