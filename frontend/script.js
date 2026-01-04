const ENV = window.location.hostname === 'localhost' ? 'dev' : 'prod';
console.log(ENV)

const BASE_URL = ENV === 'dev' 
    ? 'http://localhost:8080/api'
    : 'https://jay1261.com/api';

const MAX_CUSTOM = 200;

let fixedExtensions = [];
let customExtensions = [];

function loadExtensions() {
    $.get(`${BASE_URL}/file-extensions`, (res) => {
        if (res.httpCode !== 200) return alert('확장자 데이터를 불러오지 못했습니다.');

        const data = res.data;

        // 클래스 인스턴스로 변환
        fixedExtensions = data.fixedExtensions.map(f => new FileExtension(f));
        customExtensions = data.customExtensions.map(c => new FileExtension(c));

        // 렌더링
        $('#fixedList').empty();
        fixedExtensions.forEach(f => $('#fixedList').append(f.render()));

        $('#customList').empty();
        customExtensions.forEach(c => $('#customList').append(c.render()));

        // 카운트 업데이트
    });
}

$(document).ready(function(){
    loadExtensions();
});