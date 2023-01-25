var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":37,"id":834,"methods":[{"el":32,"sc":5,"sl":30},{"el":36,"sc":5,"sl":34}],"name":"JobBeschreibungen","sl":28}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":30}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":31}]},"test_12":{"methods":[{"sl":34}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":35}]},"test_19":{"methods":[{"sl":34}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":35}]},"test_25":{"methods":[{"sl":34}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":35}]},"test_26":{"methods":[{"sl":34}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":35}]},"test_30":{"methods":[{"sl":30}],"name":"checkLadeOneJobStatusFailure","pass":true,"statements":[{"sl":31}]},"test_31":{"methods":[{"sl":34}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":35}]},"test_38":{"methods":[{"sl":30}],"name":"checkLadeTwoJobStatusSUCCESSandUNSTABLE","pass":true,"statements":[{"sl":31}]},"test_4":{"methods":[{"sl":34}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":35}]},"test_6":{"methods":[{"sl":34}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":35}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [30, 1, 38], [30, 1, 38], [], [], [31, 4, 6, 26, 12, 19, 25], [31, 4, 6, 26, 12, 19, 25], [], []]
