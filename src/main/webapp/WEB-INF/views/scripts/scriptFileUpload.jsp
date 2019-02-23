<%--suppress JSUnusedLocalSymbols --%>

<script>
    function setFileInputPlaceholder(inputId) {
        let fileName = document.getElementById(inputId).files[0].name;
        let inputPlaceholder = document.getElementById(inputId + 'Label');
        inputPlaceholder.innerText = fileName;
    }
</script>