var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":248,"id":878,"methods":[{"el":71,"sc":5,"sl":60},{"el":78,"sc":5,"sl":73},{"el":83,"sc":5,"sl":80},{"el":92,"sc":5,"sl":85},{"el":101,"sc":5,"sl":94},{"el":118,"sc":13,"sl":111},{"el":132,"sc":5,"sl":104},{"el":149,"sc":13,"sl":142},{"el":163,"sc":5,"sl":134},{"el":175,"sc":5,"sl":166},{"el":186,"sc":5,"sl":177},{"el":197,"sc":5,"sl":188},{"el":209,"sc":5,"sl":199},{"el":228,"sc":5,"sl":211},{"el":245,"sc":5,"sl":230}],"name":"ConfigurationTest","sl":48}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_0":{"methods":[{"sl":211}],"name":"reloadCurrentConfiguration","pass":true,"statements":[{"sl":214},{"sl":215},{"sl":216},{"sl":217},{"sl":218},{"sl":219},{"sl":220},{"sl":221},{"sl":222},{"sl":223},{"sl":224},{"sl":226},{"sl":227}]},"test_15":{"methods":[{"sl":104},{"sl":111}],"name":"useDefaultConfigfile","pass":true,"statements":[{"sl":107},{"sl":108},{"sl":109},{"sl":113},{"sl":114},{"sl":121},{"sl":122},{"sl":123},{"sl":124},{"sl":125},{"sl":126},{"sl":128},{"sl":129},{"sl":130}]},"test_20":{"methods":[{"sl":94}],"name":"initAfterGetJobBeschreibungenOK","pass":true,"statements":[{"sl":97},{"sl":98},{"sl":99},{"sl":100}]},"test_26":{"methods":[{"sl":199}],"name":"useUserNameFromConfigfile","pass":true,"statements":[{"sl":202},{"sl":203},{"sl":204},{"sl":205},{"sl":206}]},"test_29":{"methods":[{"sl":230}],"name":"reloadOtherConfiguration","pass":true,"statements":[{"sl":233},{"sl":234},{"sl":235},{"sl":236},{"sl":237},{"sl":238},{"sl":240},{"sl":241},{"sl":242},{"sl":243},{"sl":244}]},"test_30":{"methods":[{"sl":166}],"name":"validDefaultsWhenNotExistingConfigfile","pass":true,"statements":[{"sl":169},{"sl":170},{"sl":172},{"sl":173},{"sl":174}]},"test_39":{"methods":[{"sl":177}],"name":"validDefaultsWithEmptyConfigfile","pass":true,"statements":[{"sl":180},{"sl":181},{"sl":183},{"sl":184},{"sl":185}]},"test_5":{"methods":[{"sl":134},{"sl":142}],"name":"useSharedDefaultConfigfile","pass":true,"statements":[{"sl":138},{"sl":139},{"sl":140},{"sl":144},{"sl":145},{"sl":152},{"sl":153},{"sl":154},{"sl":155},{"sl":156},{"sl":157},{"sl":159},{"sl":160},{"sl":161}]},"test_7":{"methods":[{"sl":188}],"name":"useDefaultPollPeriod","pass":true,"statements":[{"sl":191},{"sl":192},{"sl":194},{"sl":195},{"sl":196}]},"test_8":{"methods":[{"sl":85}],"name":"initAfterGetPollOK","pass":true,"statements":[{"sl":88},{"sl":89},{"sl":90},{"sl":91}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [8], [], [], [8], [8], [8], [8], [], [], [20], [], [], [20], [20], [20], [20], [], [], [], [15], [], [], [15], [15], [15], [], [15], [], [15], [15], [], [], [], [], [], [], [15], [15], [15], [15], [15], [15], [], [15], [15], [15], [], [], [], [5], [], [], [], [5], [5], [5], [], [5], [], [5], [5], [], [], [], [], [], [], [5], [5], [5], [5], [5], [5], [], [5], [5], [5], [], [], [], [], [30], [], [], [30], [30], [], [30], [30], [30], [], [], [39], [], [], [39], [39], [], [39], [39], [39], [], [], [7], [], [], [7], [7], [], [7], [7], [7], [], [], [26], [], [], [26], [26], [26], [26], [26], [], [], [], [], [0], [], [], [0], [0], [0], [0], [0], [0], [0], [0], [0], [0], [0], [], [0], [0], [], [], [29], [], [], [29], [29], [29], [29], [29], [29], [], [29], [29], [29], [29], [29], [], [], [], []]
