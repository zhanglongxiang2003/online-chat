import { ElMessage } from 'element-plus';


//复制文本
export const forestCopyText = (text) => {
    const textarea = document.createElement('textarea');
    textarea.value = text;
    document.body.appendChild(textarea);
    textarea.select();
    document.execCommand('copy');
    document.body.removeChild(textarea);
    ElMessage.success(`${text} 复制成功`)
}