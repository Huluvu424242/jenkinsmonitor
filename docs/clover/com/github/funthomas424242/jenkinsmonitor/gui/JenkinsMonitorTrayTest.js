var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":66,"id":989,"methods":[{"el":59,"sc":5,"sl":57},{"el":65,"sc":5,"sl":61}],"name":"JenkinsClientMock","sl":52},{"el":86,"id":994,"methods":[{"el":76,"sc":5,"sl":73},{"el":85,"sc":5,"sl":78}],"name":"JenkinsClientMockAuto","sl":68},{"el":212,"id":1001,"methods":[{"el":102,"sc":5,"sl":99},{"el":108,"sc":5,"sl":104},{"el":112,"sc":5,"sl":110},{"el":123,"sc":5,"sl":115},{"el":134,"sc":5,"sl":125},{"el":145,"sc":5,"sl":136},{"el":156,"sc":5,"sl":147},{"el":169,"sc":5,"sl":158},{"el":184,"sc":5,"sl":171},{"el":211,"sc":5,"sl":186}],"name":"JenkinsMonitorTrayTest","sl":90}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_12":{"methods":[{"sl":73},{"sl":78},{"sl":110},{"sl":186}],"name":"updateJobsAfterTimePeriod","pass":true,"statements":[{"sl":74},{"sl":75},{"sl":80},{"sl":82},{"sl":83},{"sl":111},{"sl":191},{"sl":193},{"sl":195},{"sl":196},{"sl":202},{"sl":204},{"sl":205}]},"test_26":{"methods":[{"sl":57},{"sl":61},{"sl":147}],"name":"shouldShowOneFailedJobWatching","pass":true,"statements":[{"sl":58},{"sl":63},{"sl":64},{"sl":150},{"sl":152},{"sl":154},{"sl":155}]},"test_38":{"methods":[{"sl":110},{"sl":115}],"name":"shouldShowNoJobsWatching","pass":true,"statements":[{"sl":111},{"sl":118},{"sl":119},{"sl":120},{"sl":121},{"sl":122}]},"test_40":{"methods":[{"sl":73},{"sl":78},{"sl":110},{"sl":171}],"name":"showStatusAsToolstippsIfJobsPresent","pass":true,"statements":[{"sl":74},{"sl":75},{"sl":80},{"sl":82},{"sl":83},{"sl":111},{"sl":174},{"sl":176},{"sl":178},{"sl":179}]},"test_47":{"methods":[{"sl":57},{"sl":61},{"sl":110},{"sl":136}],"name":"shouldShowOneInstabilJobWatching","pass":true,"statements":[{"sl":58},{"sl":63},{"sl":64},{"sl":111},{"sl":139},{"sl":141},{"sl":143},{"sl":144}]},"test_7":{"methods":[{"sl":73},{"sl":78},{"sl":110},{"sl":158}],"name":"showStatusAsToolstippsIfJobPresent","pass":true,"statements":[{"sl":74},{"sl":75},{"sl":80},{"sl":82},{"sl":83},{"sl":111},{"sl":161},{"sl":163},{"sl":165},{"sl":166}]},"test_9":{"methods":[{"sl":57},{"sl":61},{"sl":110},{"sl":125}],"name":"shouldShowOneSuccessJobWatching","pass":true,"statements":[{"sl":58},{"sl":63},{"sl":64},{"sl":111},{"sl":128},{"sl":130},{"sl":132},{"sl":133}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [47, 9, 26], [47, 9, 26], [], [], [47, 9, 26], [], [47, 9, 26], [47, 9, 26], [], [], [], [], [], [], [], [], [12, 7, 40], [12, 7, 40], [12, 7, 40], [], [], [12, 7, 40], [], [12, 7, 40], [], [12, 7, 40], [12, 7, 40], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [47, 12, 7, 9, 38, 40], [47, 12, 7, 9, 38, 40], [], [], [], [38], [], [], [38], [38], [38], [38], [38], [], [], [9], [], [], [9], [], [9], [], [9], [9], [], [], [47], [], [], [47], [], [47], [], [47], [47], [], [], [26], [], [], [26], [], [26], [], [26], [26], [], [], [7], [], [], [7], [], [7], [], [7], [7], [], [], [], [], [40], [], [], [40], [], [40], [], [40], [40], [], [], [], [], [], [], [12], [], [], [], [], [12], [], [12], [], [12], [12], [], [], [], [], [], [12], [], [12], [12], [], [], [], [], [], [], []]