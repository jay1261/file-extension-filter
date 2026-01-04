class FileExtension {
    constructor({ id, name, blocked = false, extensionType }) {
        this.id = id;
        this.name = name;
        this.blocked = blocked;
        this.extensionType = extensionType;
    }

    render() {
        if (this.extensionType === 'FIXED') {
            const $label = $(`
                <label>
                    <input type="checkbox" value="${this.name}" ${this.blocked ? 'checked' : ''}>
                    ${this.name}
                </label>
            `);
            
            // 체크박스 상태 변경 시 객체 갱신
            $label.find('input').change(() => {
                this.blocked = !this.blocked;
                // TODO: 서버 동기화 함수 구현
            });
            return $label;
        } else if (this.extensionType === 'CUSTOM') {
            const $tag = $(`
                <span class="tag">${this.name}<span class="remove">✖</span></span>
            `);
            $tag.find('.remove').click(() => {
                // TODO: 삭제 함수 구현
            });
            return $tag;
        }
    }
}
