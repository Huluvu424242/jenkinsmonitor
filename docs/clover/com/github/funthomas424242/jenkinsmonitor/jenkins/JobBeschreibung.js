var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":77,"id":770,"methods":[{"el":39,"sc":5,"sl":37},{"el":46,"sc":5,"sl":41},{"el":51,"sc":5,"sl":48},{"el":56,"sc":5,"sl":53},{"el":61,"sc":5,"sl":59},{"el":70,"sc":5,"sl":63},{"el":75,"sc":5,"sl":72}],"name":"JobBeschreibung","sl":29}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":41},{"sl":48},{"sl":53}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45},{"sl":50},{"sl":55}]},"test_17":{"methods":[{"sl":41},{"sl":48},{"sl":53},{"sl":59}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45},{"sl":50},{"sl":55},{"sl":60}]},"test_18":{"methods":[{"sl":41},{"sl":48},{"sl":53},{"sl":63}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45},{"sl":50},{"sl":55},{"sl":65},{"sl":66},{"sl":67},{"sl":68}]},"test_20":{"methods":[{"sl":37},{"sl":41},{"sl":48}],"name":"valideInitialisierung","pass":true,"statements":[{"sl":38},{"sl":42},{"sl":44},{"sl":45},{"sl":50}]},"test_26":{"methods":[{"sl":41},{"sl":48},{"sl":53}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45},{"sl":50},{"sl":55}]},"test_29":{"methods":[{"sl":41}],"name":"fehlerhafteJobsSindRot","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45}]},"test_31":{"methods":[{"sl":41},{"sl":48},{"sl":53},{"sl":59}],"name":"checkLadeTwoJobStatusSUCCESSandUNSTABLE","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45},{"sl":50},{"sl":55},{"sl":60}]},"test_33":{"methods":[{"sl":41},{"sl":48},{"sl":53},{"sl":59}],"name":"checkLadeOneJobStatusFailure","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45},{"sl":50},{"sl":55},{"sl":60}]},"test_34":{"methods":[{"sl":41}],"name":"erfolgreicheJobsSindGruen","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45}]},"test_38":{"methods":[{"sl":41}],"name":"instabileJobsSindGelb","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45}]},"test_39":{"methods":[{"sl":41},{"sl":48},{"sl":53},{"sl":59}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":42},{"sl":44},{"sl":45},{"sl":50},{"sl":55},{"sl":60}]},"test_6":{"methods":[{"sl":63},{"sl":72}],"name":"checkEqualsAndHashCode","pass":true,"statements":[{"sl":65},{"sl":66},{"sl":67},{"sl":68},{"sl":74}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [20], [20], [], [], [31, 20, 17, 1, 34, 26, 29, 39, 33, 38, 18], [31, 20, 17, 1, 34, 26, 29, 39, 33, 38, 18], [], [31, 20, 17, 1, 34, 26, 29, 39, 33, 38, 18], [31, 20, 17, 1, 34, 26, 29, 39, 33, 38, 18], [], [], [31, 20, 17, 1, 26, 39, 33, 18], [], [31, 20, 17, 1, 26, 39, 33, 18], [], [], [31, 17, 1, 26, 39, 33, 18], [], [31, 17, 1, 26, 39, 33, 18], [], [], [], [31, 17, 39, 33], [31, 17, 39, 33], [], [], [6, 18], [], [6, 18], [6, 18], [6, 18], [6, 18], [], [], [], [6], [], [6], [], [], [], []]
