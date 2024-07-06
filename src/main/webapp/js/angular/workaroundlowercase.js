/**
 * @brief Shim for textAngular in order to make it compatible with the latest 
 *   version of angularjs
 */

function lowercase(string) {
  return (typeof string === 'string') ? string.toLowerCase() : string;
}

// Angular deprecated the lowercase function as of v1.6.7. TextAngular hasn't 
// updated to reflect this
angular.lowercase = lowercase;