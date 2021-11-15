// 로그인 대신 임시 방편
let username = prompt("아이디를 입력하세요.")
let roomNum = prompt("채팅방 번호를 입력하세요.")

document.querySelector('#username').innerHTML = username

// SSE 연결하기
const eventSource = new EventSource(`http://localhost:8080/chat/roomNum/${roomNum}`);
eventSource.onmessage = (event) => {
	const data = JSON.parse(event.data)
	// 로그인한 유저가 보낸 메세지
	if (data.sender === username) {
		// right blue box 
		initMyMessage(data);
	} else {
		// left gray box
		initYourMessage(data);
	}
}

// blue box 
function getSendMsgBox(data) {

	let md = data.createdAt.substring(5, 10);
	let tm = data.createdAt.substring(11, 16);
	convertTime = tm + "|" + md

	return `<div class="sent_msg">
		<p>${data.msg}</p>
		<span class="time_date"> ${convertTime}/<strong> ${data.sender}</strong> </span>
	</div> `;
}

// gray box
function getReceiveMsgBox(data) {
	let md = data.createdAt.substring(5, 10);
	let tm = data.createdAt.substring(11, 16);
	convertTime = tm + "|" + md
	return `<div class="received_withd_msg">
		<p>${data.msg}</p>
		<span class="time_date"> ${convertTime}/<strong>${data.sender}</strong> </span>
	</div> `;
}


/**
 * 최초 초기화될때 1번방 3건이 있으면 3건을 다 가져온다.
 * addMessage() 함수 호출시 DB에 insert 되고, 그 데이터가 자동으로 흘러 들어온다.
 */
// 파란박스 초기화하기
function initMyMessage(data) {
	let chatBox = document.querySelector("#chat-box")

	let sendBox = document.createElement("div")
	sendBox.className = "outgoing_msg"
	sendBox.innerHTML = getSendMsgBox(data);

	chatBox.append(sendBox);
	document.documentElement.scrollTop = document.body.scrollHeight;
}

// 회색박스 초기화하기
function initYourMessage(data) {
	let chatBox = document.querySelector("#chat-box")

	let receivedBox = document.createElement("div")
	receivedBox.className = "received_msg"

	receivedBox.innerHTML = getReceiveMsgBox(data);

	chatBox.append(receivedBox);
	document.documentElement.scrollTop = document.body.scrollHeight;
}
// AJAX 채팅 메세지 전송
function addMessage() {
	let msgInput = document.querySelector('#chat-outgoing-msg')

	let chat = {
		sender: username,
		roomNum : roomNum,
		msg : msgInput.value
	}

	fetch("http://localhost:8080/chat", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(chat), // JS obj->JSON
		
	})
	msgInput.value = ""

}
// 전송버튼 클릭시
document.querySelector('#chat-send').addEventListener('click', () => {
	addMessage()
})
// input 엔터시 
document.querySelector('#chat-outgoing-msg').addEventListener('keydown', (e) => {
	if (e.keyCode === 13) {
		addMessage()
	}
})
