var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":100,"id":1293,"methods":[{"el":47,"sc":5,"sl":42},{"el":55,"sc":5,"sl":49},{"el":68,"sc":5,"sl":58},{"el":78,"sc":5,"sl":70},{"el":88,"sc":5,"sl":80},{"el":98,"sc":5,"sl":90}],"name":"JobStatusBeschreibungTest","sl":37}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_10":{"methods":[{"sl":80}],"name":"instabileJobsSindGelb","pass":true,"statements":[{"sl":83},{"sl":86},{"sl":87}]},"test_2":{"methods":[{"sl":49}],"name":"checkEqualsAndHashCode","pass":true,"statements":[{"sl":52},{"sl":53}]},"test_30":{"methods":[{"sl":58}],"name":"valideInitialisierung","pass":true,"statements":[{"sl":61},{"sl":64},{"sl":65},{"sl":66},{"sl":67}]},"test_32":{"methods":[{"sl":70}],"name":"erfolgreicheJobsSindGruen","pass":true,"statements":[{"sl":73},{"sl":76},{"sl":77}]},"test_60":{"methods":[{"sl":90}],"name":"fehlerhafteJobsSindRot","pass":true,"statements":[{"sl":93},{"sl":96},{"sl":97}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [2], [], [], [2], [2], [], [], [], [], [30], [], [], [30], [], [], [30], [30], [30], [30], [], [], [32], [], [], [32], [], [], [32], [32], [], [], [10], [], [], [10], [], [], [10], [10], [], [], [60], [], [], [60], [], [], [60], [60], [], [], []]