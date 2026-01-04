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
            
            // 체크박스 클릭시 toggle api 호출
            $label.find('input').change((e) => {
                const checkbox = e.target;
                toggleFixedBlocked(this, checkbox.checked);

            });
            return $label;
        } else if (this.extensionType === 'CUSTOM') {
            const $tag = $(`
                <span class="tag">${this.name}<span class="remove">✖</span></span>
            `);
            // 삭제 버튼 클릭 시 remove api 호출
            $tag.find('.remove').click(() => {
                removeCustomExtension(this.id);
            });
            return $tag;
        }
    }
}
