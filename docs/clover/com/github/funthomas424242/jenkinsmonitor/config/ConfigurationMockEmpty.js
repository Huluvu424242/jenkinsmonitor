var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":45,"id":882,"methods":[{"el":35,"sc":5,"sl":34},{"el":39,"sc":5,"sl":37},{"el":44,"sc":5,"sl":41}],"name":"ConfigurationMockEmpty","sl":30}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_10":{"methods":[{"sl":37},{"sl":41}],"name":"initAfterGetPollOK","pass":true,"statements":[{"sl":38},{"sl":42},{"sl":43}]},"test_12":{"methods":[{"sl":37},{"sl":41}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":38},{"sl":42},{"sl":43}]},"test_19":{"methods":[{"sl":37},{"sl":41}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":38},{"sl":42},{"sl":43}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [19, 12, 10], [19, 12, 10], [], [], [19, 12, 10], [19, 12, 10], [19, 12, 10], [], []]
