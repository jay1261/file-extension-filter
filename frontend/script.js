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
        updateCount();
    });
}

function addCustomExtension(name) {
    const nomalized = normalizeExtensionName(name);

    if (!nomalized) return alert('확장자를 입력해주세요.');
    if (customExtensions.length >= MAX_CUSTOM) return alert('최대 200개까지 추가 가능합니다.');
    if (customExtensions.some(c => c.name === nomalized)) return alert('이미 추가된 확장자입니다.');

    // 서버로 POST
    $.ajax({
        url: `${BASE_URL}/file-extensions/custom`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ name: nomalized }),
        success: function(res) {
            // 성공 시 새로운 객체 생성
            const newExt = new FileExtension(res.data);

            customExtensions.push(newExt);
            renderCustomExtensions();
            $('#customExtInput').val('');
        },
        error: function(jqXHR) {
            let msg = '확장자 추가에 실패했습니다.';
            try {
                const res = JSON.parse(jqXHR.responseText);
                if (res.message) msg = res.message;
            } catch (e) {
                // JSON 파싱 실패 시 기본 메시지 사용
            }
            alert(msg);
        }
    });
}

function removeCustomExtension(id) {
    if (!confirm('정말 삭제하시겠습니까?')) return;

    $.ajax({
        url: `${BASE_URL}/file-extensions/custom/${id}`,
        type: 'DELETE',
        success: function(res) {
            customExtensions = customExtensions.filter(c => c.id !== id);
            renderCustomExtensions();
        },
        error: function(jqXHR) {
            let msg = '삭제에 실패했습니다.';
            try {
                const res = JSON.parse(jqXHR.responseText);
                if (res.message) msg = res.message;
            } catch (e) {}
            alert(msg);
        }
    });
}

function updateCount() {
    $('#count').text(`${customExtensions.length}/${MAX_CUSTOM}`);
}

function normalizeExtensionName(name) {
    return name.trim().replace(/\./g, '').toLowerCase();
}

function renderCustomExtensions() {
    $('#customList').empty();
    customExtensions.forEach(c => $('#customList').append(c.render()));
    updateCount();
}

$(document).ready(function(){
    loadExtensions();

    $('#addCustom').click(function(){
        addCustomExtension($('#customExtInput').val());
    });

    $('#customExtInput').keypress(function(e){
        if (e.which === 13) {
            addCustomExtension($('#customExtInput').val());
            return false;
        }
    });
});

