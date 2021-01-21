// https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/A_first_splash#%EC%98%88%EC%A0%9C-%EC%88%AB%EC%9E%90%EB%A7%9E%EC%B6%94%EA%B8%B0


// 데이터 저장을 위한 변수 추가
// 프로그램에서 사용될 데이터를 저장할 변수를 세팅한다
// 값들을 저장!
var randomNumber = Math.floor(Math.random() * 100) + 1; // 수학적 알고리즘을 통해 계산해 1 ~ 100 사이의 임의의 수를 지정

var guesses = document.querySelector('.guesses'); 
var lastResult = document.querySelector('.lastResult');
var lowOrHi = document.querySelector('.lowOrHi');

var guessSubmit = document.querySelector('.guessSubmit');
var guessField = document.querySelector('.guessField'); // 정보를 얻기 위해 querySelector() 메소드 사용

var guessCount = 1;
var resetButton;


// 함수
// function checkGuess() {
//     alert('I am a placeholder');
// }

// 연산자
var name = 'Bingo';
console.log(name);
var hello = 'says hello!';
console.log(hello);
var greeting = name + ' ' + hello;
console.log(greeting);



// 조건문
function checkGuess() {
    var userGuess = Number(guessField.value);
    
    if (guessCount === 1) { // 현재 게임 플레이어가 처음인지 아닌지 확인
        guesses.textContent = 'Previous guesses : '; // 
    }
    guesses.textContent += userGuess + ' ';

    if (userGuess === randomNumber) {
        lastResult.textContent = 'Congratulations! You got it right!';
        lastResult.style.backgroundColor = 'green';
        lowOrHi.textContent = '';
        setGameOver();
    } else if (guessCount === 10) { // 10번 입력시  
        lastResult.textContent = '!!!GAME OVER!!!';
        setGameOver();
    } else {
        lastResult.textContent = 'Wrong!';
        lastResult.style.backgroundColor = 'red';

        if (userGuess < randomNumber) {
            lowOrHi.textContent = 'Last guess was too low!';
        } else if (userGuess > randomNumber) {
            lowOrHi.textContent = 'Last guess was too high!';
        }
    }

    // 입력횟수 증가
    guessCount++;
    guessField.value = '';
    guessField.focus();
}

// 버튼 이벤트 추가
guessSubmit.addEventListener('click', checkGuess); // 클릭, checkGuess() 실행


// 프로그램 종료 함수 설정
function setGameOver() {
    // disable 이란 : 작동시키지 않게 설정
    guessField.disabled = true;
    guessSubmit.disabled = true;
    // 새로운 button 생성 후 start new game 이라고 표시하게 한다.
    resetButton = document.createElement('button');
    resetButton.textContent = 'Start new game';
    document.body.appendChild(resetButton);
    // resetButton 시 resetGame 실행
    resetButton.addEventListener('click', resetGame);
}

// resetGame(게임 다시 시작) 함수 구현
function resetGame() {
    guessCount = 1;

    // querySelectorAll() 메소드 사용하여 <div class="resultParas"> 안에 모든 문장들의 배열을 변수로 만든다.
    // 반복을 통해 각각의 배열 원소에 접근하여, 내용을 제거하게 해야한다.
    var resetParas = document.querySelectorAll('.resultParas p');
    for (var i = 0 ; i < resetParas.length ; i++) {
        resetParas[i].textContent = '';
    }

    resetButton.parentNode.removeChild(resetButton);

    guessField.disabled = false;
    guessSubmit.disabled = false;
    guessField.value = '';
     // 자동으로 커서를 페이지가 뜨자마자 <input> 텍스트 필드에 위치 시킬 수 있다.
     // 따라서 사용자가 처음에 폼 필드를 클릭할 필요없이 바로 글을 쓸 수 있게 된다.
    guessField.focus();

    lastResult.style.backgroundColor = 'white';

    randomNumber = Math.floor(Math.random() * 100) + 1;
}