var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":80,"id":572,"methods":[{"el":36,"sc":5,"sl":34},{"el":40,"sc":5,"sl":38},{"el":44,"sc":5,"sl":42},{"el":48,"sc":5,"sl":46},{"el":52,"sc":5,"sl":50},{"el":56,"sc":5,"sl":54},{"el":60,"sc":5,"sl":58},{"el":66,"sc":5,"sl":62},{"el":74,"sc":5,"sl":68},{"el":79,"sc":5,"sl":76}],"name":"AbstractJobBeschreibungen","sl":30}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":38},{"sl":42}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_10":{"methods":[{"sl":38}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":39}]},"test_12":{"methods":[{"sl":38},{"sl":42},{"sl":50}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51}]},"test_13":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58},{"sl":62}],"name":"initTwoJobsOneGreenOneRedIcon50x100","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59},{"sl":63},{"sl":64},{"sl":65}]},"test_14":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59}]},"test_2":{"methods":[{"sl":38},{"sl":42}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_21":{"methods":[{"sl":38},{"sl":42},{"sl":68}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":70},{"sl":71},{"sl":72},{"sl":73}]},"test_25":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58},{"sl":62}],"name":"initOneJobRedIcon100x100","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59},{"sl":63},{"sl":64},{"sl":65}]},"test_26":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58},{"sl":62}],"name":"initOneJobGreenIcon100x100","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59},{"sl":63},{"sl":64},{"sl":65}]},"test_27":{"methods":[{"sl":38},{"sl":42}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_30":{"methods":[{"sl":38},{"sl":42}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_35":{"methods":[{"sl":38},{"sl":42}],"name":"initEmptyCreateGrayIcon100x100","pass":true,"statements":[{"sl":39},{"sl":43}]},"test_36":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58},{"sl":62}],"name":"initOneJobYellowIcon100x100","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59},{"sl":63},{"sl":64},{"sl":65}]},"test_37":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58}],"name":"checkLadeTwoJobStatusSUCCESSandUNSTABLE","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59}]},"test_5":{"methods":[{"sl":38},{"sl":42},{"sl":50},{"sl":54},{"sl":58}],"name":"checkLadeOneJobStatusFailure","pass":true,"statements":[{"sl":39},{"sl":43},{"sl":51},{"sl":55},{"sl":59}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [12, 30, 5, 2, 13, 10, 37, 14, 36, 25, 1, 21, 27, 35, 26], [12, 30, 5, 2, 13, 10, 37, 14, 36, 25, 1, 21, 27, 35, 26], [], [], [12, 30, 5, 2, 13, 37, 14, 36, 25, 1, 21, 27, 35, 26], [12, 30, 5, 2, 13, 37, 14, 36, 25, 1, 21, 27, 35, 26], [], [], [], [], [], [], [12, 5, 13, 37, 14, 36, 25, 26], [12, 5, 13, 37, 14, 36, 25, 26], [], [], [5, 13, 37, 14, 36, 25, 26], [5, 13, 37, 14, 36, 25, 26], [], [], [5, 13, 37, 14, 36, 25, 26], [5, 13, 37, 14, 36, 25, 26], [], [], [13, 36, 25, 26], [13, 36, 25, 26], [13, 36, 25, 26], [13, 36, 25, 26], [], [], [21], [], [21], [21], [21], [21], [], [], [], [], [], [], []]
