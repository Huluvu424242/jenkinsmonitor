var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":41,"id":843,"methods":[{"el":35,"sc":5,"sl":33},{"el":40,"sc":5,"sl":37}],"name":"ConfigurationMockEmpty","sl":29}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_21":{"methods":[{"sl":33},{"sl":37}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_35":{"methods":[{"sl":33},{"sl":37}],"name":"initAfterGetPollOK","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]},"test_41":{"methods":[{"sl":33},{"sl":37}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":34},{"sl":38},{"sl":39}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [41, 21, 35], [41, 21, 35], [], [], [41, 21, 35], [41, 21, 35], [41, 21, 35], [], []]
