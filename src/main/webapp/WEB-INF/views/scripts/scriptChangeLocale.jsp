<%--suppress JSUnusedLocalSymbols --%>

<script>
    function changeLocale(locale) {
        document.cookie = 'locale=' + locale + ';path=/';
        window.location.reload();
    }
</script>