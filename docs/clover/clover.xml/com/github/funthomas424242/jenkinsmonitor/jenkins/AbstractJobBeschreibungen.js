var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":80,"id":572,"methods":[{"el":36,"sc":5,"sl":34},{"el":40,"sc":5,"sl":38},{"el":44,"sc":5,"sl":42},{"el":48,"sc":5,"sl":46},{"el":52,"sc":5,"sl":50},{"el":56,"sc":5,"sl":54},{"el":60,"sc":5,"sl":58},{"el":66,"sc":5,"sl":62},{"el":74,"sc":5,"sl":68},{"el":79,"sc":5,"sl":76}],"name":"AbstractJobBeschreibungen","sl":30}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_0":{"methods":[{"sl":38},{"sl":42}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_1":{"methods":[{"sl":38},{"sl":42}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_11":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58}],"name":"checkLadeTwoJobStatusSUCCESSandUNSTABLE","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59}]},"test_12":{"methods":[{"sl":38},{"sl":42},{"sl":68}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":70},{"sl":71},{"sl":72},{"sl":73}]},"test_17":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58},{"sl":62}],"name":"initOneJobGreenIcon100x100","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59},{"sl":63},{"sl":64},{"sl":65}]},"test_20":{"methods":[{"sl":38}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":39}]},"test_22":{"methods":[{"sl":38},{"sl":42}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_28":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58},{"sl":62}],"name":"initOneJobRedIcon100x100","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59},{"sl":63},{"sl":64},{"sl":65}]},"test_33":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58}],"name":"checkLadeOneJobStatusFailure","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59}]},"test_37":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58},{"sl":62}],"name":"initOneJobYellowIcon100x100","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59},{"sl":63},{"sl":64},{"sl":65}]},"test_38":{"methods":[{"sl":38},{"sl":42},{"sl":50}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51}]},"test_41":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58},{"sl":62}],"name":"initTwoJobsOneGreenOneRedIcon50x100","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59},{"sl":63},{"sl":64},{"sl":65}]},"test_43":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59}]},"test_5":{"methods":[{"sl":38},{"sl":42}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_7":{"methods":[{"sl":38},{"sl":42}],"name":"initEmptyCreateGrayIcon100x100","pass":true,"statements":[{"sl":39},{"sl":43}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [5, 20, 12, 28, 37, 1, 0, 11, 33, 41, 17, 7, 43, 22, 38], [5, 20, 12, 28, 37, 1, 0, 11, 33, 41, 17, 7, 43, 22, 38], [], [], [5, 12, 28, 37, 1, 0, 11, 33, 41, 17, 7, 43, 22, 38], [5, 12, 28, 37, 1, 0, 11, 33, 41, 17, 7, 43, 22, 38], [], [], [], [], [], [], [28, 37, 11, 33, 41, 17, 43, 38], [28, 37, 11, 33, 41, 17, 43, 38], [], [], [28, 37, 11, 33, 41, 17, 43], [28, 37, 11, 33, 41, 17, 43], [], [], [28, 37, 11, 33, 41, 17, 43], [28, 37, 11, 33, 41, 17, 43], [], [], [28, 37, 41, 17], [28, 37, 41, 17], [28, 37, 41, 17], [28, 37, 41, 17], [], [], [12], [], [12], [12], [12], [12], [], [], [], [], [], [], []]
