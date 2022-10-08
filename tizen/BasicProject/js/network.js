// function for low priority job
function myLowPriorityJob() {
	var today = new Date();
	  document.getElementById('estado').innerHTML = 'A ' + today;	  
	  setTimeout(myLowPriorityJob, 20000);
}

setTimeout(myLowPriorityJob, 20000);
