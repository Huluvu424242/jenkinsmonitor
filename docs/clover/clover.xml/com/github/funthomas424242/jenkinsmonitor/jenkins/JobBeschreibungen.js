var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":37,"id":799,"methods":[{"el":32,"sc":5,"sl":30},{"el":36,"sc":5,"sl":34}],"name":"JobBeschreibungen","sl":28}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_11":{"methods":[{"sl":34}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":35}]},"test_16":{"methods":[{"sl":30}],"name":"checkLadeOneJobStatusFailure","pass":true,"statements":[{"sl":31}]},"test_26":{"methods":[{"sl":30}],"name":"checkLadeTwoJobStatusSUCCESSandUNSTABLE","pass":true,"statements":[{"sl":31}]},"test_27":{"methods":[{"sl":34}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":35}]},"test_3":{"methods":[{"sl":34}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":35}]},"test_30":{"methods":[{"sl":34}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":35}]},"test_31":{"methods":[{"sl":30}],"name":"checkLadeStatusWithException","pass":true,"statements":[{"sl":31}]},"test_32":{"methods":[{"sl":34}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":35}]},"test_40":{"methods":[{"sl":34}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":35}]},"test_7":{"methods":[{"sl":34}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":35}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [26, 31, 16], [26, 31, 16], [], [], [32, 27, 30, 3, 7, 40, 11], [32, 27, 30, 3, 7, 40, 11], [], []]
