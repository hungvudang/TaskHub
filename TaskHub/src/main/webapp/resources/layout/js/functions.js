var functions = {
	scrollTo: function(idOrElement) {
		$element = (typeof idOrElement === "string") ? $("#" + idOrElement) : $(idOrElement);
		if ($element.length) {
			$([document.documentElement, document.body]).animate({ scrollTop: $element.offset().top }, 500);
		}
	}
}

//Following BalusC's recommendation to solve the problem using JavaScript, I wrote some jQuery code to do the job:

var disableEnterKeyFormSubmission = function(event) {
	if (event.keyCode == 13) {// keyCode for enter key is 13    
        var eventTarget = event.target;

        if ($(eventTarget).hasClass("js-self-handle-enter-key")) {
        	return;
        }
        
        if (eventTarget.tagName.toLowerCase() == "input" && eventTarget.type != null) {
            var inputType = eventTarget.type.toLowerCase();          
            
            if (inputType != "submit" && inputType != "reset" && inputType != "button" && inputType != "image") {
	        	eventTarget.blur();
	        	
	        	// PrimeFaces date picker (p:calendar)
	        	// need to prevent dateSelect event from happening
	        	if ($(eventTarget).hasClass("hasDatepicker")) {
	        		event.stopPropagation();
	        	}
	        	
	        	event.preventDefault();        	
            }
        }
    }
};