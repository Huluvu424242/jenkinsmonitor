var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":181,"id":8,"methods":[{"el":79,"sc":5,"sl":67},{"el":84,"sc":5,"sl":81},{"el":92,"sc":5,"sl":90},{"el":105,"sc":5,"sl":94},{"el":109,"sc":5,"sl":107},{"el":122,"sc":5,"sl":111},{"el":138,"sc":5,"sl":124},{"el":143,"sc":5,"sl":141},{"el":149,"sc":5,"sl":145},{"el":155,"sc":5,"sl":151},{"el":179,"sc":5,"sl":157}],"name":"Configuration","sl":47}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_12":{"methods":[{"sl":94},{"sl":151},{"sl":157}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":102},{"sl":104},{"sl":152},{"sl":153},{"sl":154},{"sl":158},{"sl":159},{"sl":178}]},"test_13":{"methods":[{"sl":94},{"sl":151},{"sl":157}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":104},{"sl":152},{"sl":153},{"sl":154},{"sl":158},{"sl":159},{"sl":178}]},"test_15":{"methods":[{"sl":81},{"sl":94},{"sl":107},{"sl":111},{"sl":124},{"sl":145},{"sl":157}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":82},{"sl":83},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":104},{"sl":108},{"sl":112},{"sl":113},{"sl":114},{"sl":119},{"sl":121},{"sl":125},{"sl":126},{"sl":127},{"sl":129},{"sl":133},{"sl":134},{"sl":146},{"sl":147},{"sl":148},{"sl":158},{"sl":159},{"sl":165},{"sl":166},{"sl":167},{"sl":168},{"sl":169},{"sl":170},{"sl":171},{"sl":172},{"sl":178}]},"test_19":{"methods":[{"sl":94},{"sl":111},{"sl":124},{"sl":151},{"sl":157}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":104},{"sl":112},{"sl":113},{"sl":114},{"sl":119},{"sl":121},{"sl":125},{"sl":126},{"sl":127},{"sl":129},{"sl":133},{"sl":134},{"sl":152},{"sl":153},{"sl":154},{"sl":158},{"sl":159},{"sl":165},{"sl":166},{"sl":167},{"sl":168},{"sl":169},{"sl":170},{"sl":171},{"sl":172},{"sl":178}]},"test_23":{"methods":[{"sl":67},{"sl":81},{"sl":90},{"sl":107}],"name":"useDefaultConfigfile","pass":true,"statements":[{"sl":68},{"sl":69},{"sl":70},{"sl":82},{"sl":83},{"sl":91},{"sl":108}]},"test_3":{"methods":[{"sl":67},{"sl":81},{"sl":90},{"sl":107}],"name":"useSharedDefaultConfigfile","pass":true,"statements":[{"sl":68},{"sl":69},{"sl":72},{"sl":73},{"sl":76},{"sl":82},{"sl":83},{"sl":91},{"sl":108}]},"test_36":{"methods":[{"sl":81},{"sl":94},{"sl":151}],"name":"initAfterGetPollOK","pass":true,"statements":[{"sl":82},{"sl":83},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":104},{"sl":152},{"sl":153},{"sl":154}]},"test_6":{"methods":[{"sl":81},{"sl":94},{"sl":157}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":82},{"sl":83},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":104},{"sl":158},{"sl":159},{"sl":178}]},"test_7":{"methods":[{"sl":94},{"sl":111},{"sl":124},{"sl":141},{"sl":145},{"sl":151},{"sl":157}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":104},{"sl":112},{"sl":113},{"sl":114},{"sl":119},{"sl":121},{"sl":125},{"sl":126},{"sl":127},{"sl":129},{"sl":133},{"sl":134},{"sl":142},{"sl":146},{"sl":147},{"sl":148},{"sl":152},{"sl":153},{"sl":154},{"sl":158},{"sl":159},{"sl":165},{"sl":166},{"sl":167},{"sl":168},{"sl":169},{"sl":170},{"sl":171},{"sl":172},{"sl":178}]},"test_9":{"methods":[{"sl":94},{"sl":111},{"sl":124},{"sl":157}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":104},{"sl":112},{"sl":113},{"sl":114},{"sl":119},{"sl":121},{"sl":125},{"sl":126},{"sl":127},{"sl":129},{"sl":133},{"sl":134},{"sl":158},{"sl":159},{"sl":165},{"sl":166},{"sl":167},{"sl":168},{"sl":169},{"sl":170},{"sl":171},{"sl":172},{"sl":178}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [3, 23], [3, 23], [3, 23], [23], [], [3], [3], [], [], [3], [], [], [], [], [3, 15, 23, 6, 36], [3, 15, 23, 6, 36], [3, 15, 23, 6, 36], [], [], [], [], [], [], [3, 23], [3, 23], [], [], [9, 7, 15, 13, 6, 12, 36, 19], [9, 7, 15, 13, 6, 12, 36, 19], [9, 7, 15, 13, 6, 12, 36, 19], [9, 7, 15, 13, 6, 12, 36, 19], [9, 7, 15, 13, 6, 12, 36, 19], [9, 7, 15, 13, 6, 36, 19], [9, 7, 15, 13, 6, 36, 19], [], [12], [], [9, 7, 15, 13, 6, 12, 36, 19], [], [], [3, 15, 23], [3, 15, 23], [], [], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [], [], [], [], [9, 7, 15, 19], [], [9, 7, 15, 19], [], [], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [], [9, 7, 15, 19], [], [], [], [9, 7, 15, 19], [9, 7, 15, 19], [], [], [], [], [], [], [7], [7], [], [], [7, 15], [7, 15], [7, 15], [7, 15], [], [], [7, 13, 12, 36, 19], [7, 13, 12, 36, 19], [7, 13, 12, 36, 19], [7, 13, 12, 36, 19], [], [], [9, 7, 15, 13, 6, 12, 19], [9, 7, 15, 13, 6, 12, 19], [9, 7, 15, 13, 6, 12, 19], [], [], [], [], [], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [9, 7, 15, 19], [], [], [], [], [], [9, 7, 15, 13, 6, 12, 19], [], [], []]
