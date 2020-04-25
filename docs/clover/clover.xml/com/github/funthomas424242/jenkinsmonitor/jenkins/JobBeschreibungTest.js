var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":89,"id":1271,"methods":[{"el":43,"sc":5,"sl":40},{"el":53,"sc":5,"sl":45},{"el":63,"sc":5,"sl":56},{"el":71,"sc":5,"sl":65},{"el":79,"sc":5,"sl":73},{"el":87,"sc":5,"sl":81}],"name":"JobBeschreibungTest","sl":35}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":81}],"name":"fehlerhafteJobsSindRot","pass":true,"statements":[{"sl":84},{"sl":86}]},"test_2":{"methods":[{"sl":65}],"name":"erfolgreicheJobsSindGruen","pass":true,"statements":[{"sl":68},{"sl":70}]},"test_20":{"methods":[{"sl":73}],"name":"instabileJobsSindGelb","pass":true,"statements":[{"sl":76},{"sl":78}]},"test_4":{"methods":[{"sl":56}],"name":"valideInitialisierung","pass":true,"statements":[{"sl":59},{"sl":61},{"sl":62}]},"test_53":{"methods":[{"sl":45}],"name":"checkEqualsAndHashCode","pass":true,"statements":[{"sl":48},{"sl":49}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [53], [], [], [53], [53], [], [], [], [], [], [], [4], [], [], [4], [], [4], [4], [], [], [2], [], [], [2], [], [2], [], [], [20], [], [], [20], [], [20], [], [], [1], [], [], [1], [], [1], [], [], []]
