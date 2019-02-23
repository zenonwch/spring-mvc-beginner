<%--suppress ELValidationInJSP, JSUnusedLocalSymbols --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
    function onInput(element) {
        element.setCustomValidity('');
        return !element.validity.valid && element.setCustomValidity(' ');
    }

    function onInvalid(element, messages) {
        let validity = element.validity;
        if (validity === null || validity === '' || validity === undefined)
            return element.setCustomValidity('');

        if (typeof messages !== 'object' || messages === null || (validity.badInput && !messages.badInput))
            return element.setCustomValidity('<spring:message code="invalid.field.badInput"/>');

        let errMsg = '';
        // https://developer.mozilla.org/en-US/docs/Web/API/ValidityState
        if (validity.stepMismatch && messages.stepMismatch) errMsg = messages.stepMismatch;
        if (validity.patternMismatch && messages.patternMismatch) errMsg = messages.patternMismatch;
        if (validity.typeMismatch && messages.typeMismatch) errMsg = messages.typeMismatch;
        if (validity.rangeOverflow && messages.rangeOverflow) errMsg = messages.rangeOverflow;
        if (validity.rangeUnderflow && messages.rangeUnderflow) errMsg = messages.rangeUnderflow;
        if (validity.tooLong && messages.tooLong) errMsg = messages.tooLong;
        if (validity.tooShort && messages.tooShort) errMsg = messages.tooShort;
        if (validity.valueMissing && messages.valueMissing) errMsg = messages.valueMissing;
        if (validity.badInput && messages.badInput) errMsg = messages.badInput;

        return element.setCustomValidity(errMsg);
    }
</script>