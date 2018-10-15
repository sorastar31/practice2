var menuModule = angular.module("menu-app",[]);
//[]안에는 함께 사용할  dependency들을 넣어주면 됨

menuModule.controller("menu-controller", function($scope){
    $scope.hi = "안녕하세요";
    $scope.title = "메뉴명을 입력하세요";
});









/*var name = "서해물";

var text = `<span>동해물과 ${name} 그리고 백두산이</span> 
				마르고 닳도록`;

console.log(text);*/

//var text = '𠮷';
//console.log(text.length);

//var pat = "@";
//var y = "hello@naver.com";
//var text = '길';
/*var text = '𠮷';
console.log(/^.$/.test(text));*/
//정규식에 u flag가 포함되쓔
/*var text = '𠮷';
console.log(/^.$/u.test(text));*/


/*let x = 3;
    
console.log(x);
console.log(window.x);
    
console.log(window.x === x);*/

/*window.addEventListener("load",function(){
    var x = 3;
    var x = 5;
    
    let x = 3;
    let x = 5;
    
    //console.log(x);

    function getValue(condition){
        if(condition){
            var value = "blue";
            return value;
        }else{
            return value;
        }
    };
	function getValue(condition){
        if(condition){
            let value = "blue";
            return value;
        }else{
            return value;
        }
    };
    
    alert(getValue(true));
    alert(getValue(false));

});*/



