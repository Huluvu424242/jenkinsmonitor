var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":105,"id":237,"methods":[{"el":48,"sc":5,"sl":46},{"el":52,"sc":5,"sl":50},{"el":62,"sc":5,"sl":54},{"el":66,"sc":5,"sl":64},{"el":82,"sc":5,"sl":68},{"el":87,"sc":5,"sl":84},{"el":92,"sc":5,"sl":89},{"el":104,"sc":5,"sl":94}],"name":"JenkinsMonitorTray","sl":34}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_0":{"methods":[{"sl":54},{"sl":64},{"sl":68}],"name":"showStatusAsToolstippsIfJobsPresent","pass":true,"statements":[{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":65},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81}]},"test_10":{"methods":[{"sl":54},{"sl":64},{"sl":68}],"name":"shouldShowOneInstabilJobWatching","pass":true,"statements":[{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":65},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81}]},"test_12":{"methods":[{"sl":68},{"sl":89},{"sl":94}],"name":"statusMultibranchJobGrauBuildend","pass":true,"statements":[{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":91},{"sl":95},{"sl":96},{"sl":97},{"sl":101},{"sl":102},{"sl":103}]},"test_14":{"methods":[{"sl":54},{"sl":64},{"sl":68}],"name":"showStatusAsToolstippsIfJobPresent","pass":true,"statements":[{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":65},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81}]},"test_15":{"methods":[{"sl":54},{"sl":64},{"sl":68}],"name":"shouldShowOneSuccessJobWatching","pass":true,"statements":[{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":65},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81}]},"test_16":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"trayIconHasGreenImage","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_23":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"trayIconHasRedImage","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_28":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"afterInitTrayIconExists","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_29":{"methods":[{"sl":68},{"sl":89},{"sl":94}],"name":"getStatusGreen","pass":true,"statements":[{"sl":69},{"sl":70},{"sl":73},{"sl":91},{"sl":95},{"sl":96},{"sl":97},{"sl":101},{"sl":102}]},"test_3":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"equalsConfigWithTrayAndMonitor","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_30":{"methods":[{"sl":68},{"sl":89},{"sl":94}],"name":"checkEqualsAndHashCode","pass":true,"statements":[{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":91},{"sl":95},{"sl":96},{"sl":97},{"sl":101},{"sl":102}]},"test_35":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"trayIconHasGrayImage","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_39":{"methods":[{"sl":54},{"sl":64},{"sl":68}],"name":"shouldShowOneFailedJobWatching","pass":true,"statements":[{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":65},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81}]},"test_43":{"methods":[{"sl":50},{"sl":54},{"sl":64},{"sl":68},{"sl":84}],"name":"shouldShowNoJobsWatching","pass":true,"statements":[{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":65},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_44":{"methods":[{"sl":54},{"sl":64},{"sl":68},{"sl":89},{"sl":94}],"name":"updateJobsAfterTimePeriod","pass":true,"statements":[{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":65},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":91},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":101},{"sl":102},{"sl":103}]},"test_46":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"checkTrayInstanz","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_48":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"initialConfigWithEmptyJobs","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_57":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"checkMainEmptyParaMethod","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]},"test_58":{"methods":[{"sl":68},{"sl":89},{"sl":94}],"name":"statusMultibranchJobGelb","pass":true,"statements":[{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":91},{"sl":95},{"sl":96},{"sl":97},{"sl":101},{"sl":102},{"sl":103}]},"test_7":{"methods":[{"sl":46},{"sl":50},{"sl":54},{"sl":68},{"sl":84}],"name":"checkMainMethod","pass":true,"statements":[{"sl":47},{"sl":51},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":69},{"sl":70},{"sl":73},{"sl":74},{"sl":77},{"sl":80},{"sl":81},{"sl":86}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [23, 3, 48, 57, 16, 35, 46, 28, 7], [23, 3, 48, 57, 16, 35, 46, 28, 7], [], [], [23, 3, 48, 57, 16, 35, 46, 28, 7, 43], [23, 3, 48, 57, 16, 35, 46, 28, 7, 43], [], [], [39, 23, 3, 48, 10, 57, 14, 15, 44, 16, 0, 35, 46, 28, 7, 43], [39, 23, 3, 48, 10, 57, 14, 15, 44, 16, 0, 35, 46, 28, 7, 43], [39, 23, 3, 48, 10, 57, 14, 15, 44, 16, 0, 35, 46, 28, 7, 43], [39, 23, 3, 48, 10, 57, 14, 15, 44, 16, 0, 35, 46, 28, 7, 43], [39, 23, 3, 48, 10, 57, 14, 15, 44, 16, 0, 35, 46, 28, 7, 43], [39, 23, 3, 48, 10, 57, 14, 15, 44, 16, 0, 35, 46, 28, 7, 43], [39, 23, 3, 48, 10, 57, 14, 15, 44, 16, 0, 35, 46, 28, 7, 43], [39, 23, 3, 48, 10, 57, 14, 15, 44, 16, 0, 35, 46, 28, 7, 43], [], [], [39, 10, 14, 15, 44, 0, 43], [39, 10, 14, 15, 44, 0, 43], [], [], [39, 23, 3, 48, 10, 57, 14, 29, 15, 58, 44, 16, 0, 35, 12, 46, 28, 7, 43, 30], [39, 23, 3, 48, 10, 57, 14, 29, 15, 58, 44, 16, 0, 35, 12, 46, 28, 7, 43, 30], [39, 23, 3, 48, 10, 57, 14, 29, 15, 58, 44, 16, 0, 35, 12, 46, 28, 7, 43, 30], [], [], [39, 23, 3, 48, 10, 57, 14, 29, 15, 58, 44, 16, 0, 35, 12, 46, 28, 7, 43, 30], [39, 23, 3, 48, 10, 57, 14, 15, 58, 44, 16, 0, 35, 12, 46, 28, 7, 43, 30], [], [], [39, 23, 3, 48, 10, 57, 14, 15, 58, 44, 16, 0, 35, 12, 46, 28, 7, 43, 30], [], [], [39, 23, 3, 48, 10, 57, 14, 15, 58, 44, 16, 0, 35, 12, 46, 28, 7, 43], [39, 23, 3, 48, 10, 57, 14, 15, 58, 44, 16, 0, 35, 12, 46, 28, 7, 43], [], [], [23, 3, 48, 57, 16, 35, 46, 28, 7, 43], [], [23, 3, 48, 57, 16, 35, 46, 28, 7, 43], [], [], [29, 58, 44, 12, 30], [], [29, 58, 44, 12, 30], [], [], [29, 58, 44, 12, 30], [29, 58, 44, 12, 30], [29, 58, 44, 12, 30], [29, 58, 44, 12, 30], [44], [44], [], [29, 58, 44, 12, 30], [29, 58, 44, 12, 30], [58, 44, 12], [], []]
