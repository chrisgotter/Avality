// question 4
function verifyHandler() {
  const lisp = document.getElementById('code').value;
  const verbose = document.getElementById('verbose').checked;
  const outputElement = document.getElementById('result');
  alert(verifyParentheses(lisp, verbose ? outputElement : undefined));
  if (verbose) {
  	outputElement.scrollIntoView();
  } else {
  	outputElement.innerHTML = '';
  }
}
/**
 * Verifies that all parentheses in a snippit of lisp code have mates. 
 * @param {string} lisp The code to be validated.
 * @param {string?} outputElement An element to 
 *     write the result to (optional).
 */
function verifyParentheses(lisp, outputElement) {
  let count = 0;
  let lines = lisp.split('\n');
  let fail = false;
  let failLine;
  let failChar;
  let inString = false;
  for (let lineNum = 0; lineNum < lines.length; lineNum++) {
  	let line = lines[lineNum];
  	if (!fail) {
    	for (let charNum = 0; charNum < line.length; charNum++) {
      	const char = line[charNum];
        if (inString) {
        	if (char === '"') {
          	inString = false;
          }
        } else if (char === '(') {
          count++;
        } else if (char === ')') {
          count--;
          if (count < 0) {
            line = line.substring(0, charNum) + '<span style="color:red;text-decoration: underline;" id="error">' + char + '</span>' + line.substring(charNum + 1, line.length);
            fail = true;
            failLine = lineNum + 1;
            failChar = charNum + 1;
            break;
          }
        } else if (char === ';') {
					break;
        } else if (char === '"') {
          inString = true;
        }
      }
    }
    lines[lineNum] = (lineNum + 1) + '.\t' + line;
  }
  if (typeof outputElement !== 'undefined') {
    let result = lines.join('\n');
    if (fail) {

      outputElement.innerHTML = '<hr/><span style="color: red;font-weight:bold;">FAILURE</span><br/>' + result + '<span style="color: red;" id="error"></span><hr/><a href="#error" style="color:red;">Unexpected \')\' on line: ' + failLine + ', char: ' + failChar + '</a>';
    }
    else if (count > 0) {
      fail = true;
      failLine = lines.length;
      failChar = lines[lines.length-1].length;
      outputElement.innerHTML = '<hr/><span style="color: red;font-weight:bold;">FAILURE</span><br/>' + result + '<span style="color: red;" id="error">*</span><hr/><a href="#error" style="color:red;">Expected \')\' on line: ' + failLine + ', char: ' + failChar + '</a>';
    } else {
      outputElement.innerHTML = '<hr/><span style="color: green;font-weight:bold;">SUCCESS</span><br/>' + result;
    }
  } else if (count > 0) {
  	fail = true;
  }
  return !fail;
}