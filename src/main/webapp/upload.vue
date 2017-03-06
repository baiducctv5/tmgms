<template>
    <div>
        <h3 v-if="comTitle">{{comTitle}}</h3>

        <div>
            <h2>{{nameCn}}</h2>

            <div :style="bgUrl">
                <!--<img :src="imgUrl">-->
            </div>
            <p v-if="tip">{{tip}}</p>
            <!--<div v-if="isUpload" class="upload-btn upload-true">ͼƬ���ύ</div>-->
            <div v-if="!isUpload" onclick="document.querySelector('input').click()" @click="inputBtn(name, $event)">
                �ϴ�ͼƬ
            </div>
        </div>
    </div>
</template>

<script>
    /*
     * name����Ҫ����input�������ͨ��id��������
     * nameCn�������title
     * imgUrl����������õ�û��ɾ����
     * isUpload�� ��ʶ��û���ϴ���������ڸ�����н����жϣ�����Ѿ��ϴ��˻���ϴ���ť���ص�
     * tip��ͼƬ�²���˵������
     * bgUrl��Ĭ�ϵ�˵������ͼ���ϴ���ɺ���ڸ�����и������ͼƬ�ĳ�һ���ϴ���ɵ���ʾͼ
     */
    props: ['name', 'nameCn', 'imgUrl', 'isUpload', 'tip', 'bgUrl', 'comTitle'],
            computed
    :
    {
        nameId()
        {
            return this.name + 'content'
        }
    }
</script>