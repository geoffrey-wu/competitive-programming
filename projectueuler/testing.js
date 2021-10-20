var lengthOfLongestSubstring = function (s) {
    let i = 0, j = 0;
    let currentString = '';
    let maxLength = 0;

    while (j < s.length) {
        if (currentString.length != 0 && currentString.includes(s[j])) {
            if (j - i > maxLength) maxLength = j - i;
            let position = currentString.search(s[j]) + 1;
            i += position;
            currentString = currentString.substring(position);
        }
        currentString += s[j];
        j++;
    }

    if (j - i > maxLength) maxLength = j - i;

    return maxLength;
};

console.log(lengthOfLongestSubstring('abcde'))
