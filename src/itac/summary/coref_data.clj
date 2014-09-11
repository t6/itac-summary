(ns itac.summary.coref-data)

(def corefs
  {32
   [{:head-index 10,
     :gender :unknown,
     :position [5 4],
     :mention-id 32,
     :end-index 11,
     :coref-cluster-id 32,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 5,
     :mention-span "this week",
     :number :singular,
     :start-index 9}],
   64
   [{:head-index 18,
     :gender :unknown,
     :position [8 5],
     :mention-id 64,
     :end-index 23,
     :coref-cluster-id 64,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 8,
     :mention-span "months of coming into office",
     :number :singular,
     :start-index 18}],
   96
   [{:head-index 28,
     :gender :unknown,
     :position [13 7],
     :mention-id 96,
     :end-index 38,
     :coref-cluster-id 96,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 13,
     :mention-span
     "one term but a vision which will look foard 20 years",
     :number :singular,
     :start-index 27}],
   128
   [{:head-index 8,
     :gender :neutral,
     :position [17 3],
     :mention-id 128,
     :end-index 14,
     :coref-cluster-id 128,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 17,
     :mention-span "a Scotland which does education for all",
     :number :singular,
     :start-index 7}],
   97
   [{:head-index 28,
     :gender :unknown,
     :position [13 8],
     :mention-id 97,
     :end-index 29,
     :coref-cluster-id 97,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 13,
     :mention-span "one term",
     :number :singular,
     :start-index 27}],
   2
   [{:head-index 9,
     :gender :neutral,
     :position [1 3],
     :mention-id 2,
     :end-index 10,
     :coref-cluster-id 2,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 1,
     :mention-span "first",
     :number :singular,
     :start-index 9}
    {:head-index 1,
     :gender :neutral,
     :position [3 2],
     :mention-id 17,
     :end-index 2,
     :coref-cluster-id 2,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 3,
     :mention-span "First",
     :number :singular,
     :start-index 1}],
   34
   [{:head-index 5,
     :gender :male,
     :position [5 2],
     :mention-id 34,
     :end-index 6,
     :coref-cluster-id 34,
     :animacy :animate,
     :mention-type :proper,
     :sent-num 5,
     :mention-span "Mr Salmond",
     :number :singular,
     :start-index 4}],
   98
   [{:head-index 31,
     :gender :neutral,
     :position [13 10],
     :mention-id 98,
     :end-index 38,
     :coref-cluster-id 98,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 13,
     :mention-span "a vision which will look foard 20 years",
     :number :singular,
     :start-index 30}],
   130
   [{:head-index 11,
     :gender :neutral,
     :position [17 4],
     :mention-id 130,
     :end-index 14,
     :coref-cluster-id 130,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 17,
     :mention-span "education for all",
     :number :singular,
     :start-index 11}],
   3
   [{:head-index 5,
     :gender :male,
     :position [1 1],
     :mention-id 3,
     :end-index 6,
     :coref-cluster-id 3,
     :animacy :animate,
     :mention-type :proper,
     :sent-num 1,
     :mention-span "Scottish Labor leader Johann Lamont",
     :number :singular,
     :start-index 1}],
   67
   [{:head-index 6,
     :gender :neutral,
     :position [9 2],
     :mention-id 67,
     :end-index 7,
     :coref-cluster-id 67,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 9,
     :mention-span "child tax credits",
     :number :plural,
     :start-index 4}],
   4
   [{:head-index 10,
     :gender :male,
     :position [1 2],
     :mention-id 4,
     :end-index 11,
     :coref-cluster-id 4,
     :animacy :animate,
     :mention-type :nominal,
     :sent-num 1,
     :mention-span "the first minister",
     :number :singular,
     :start-index 8}],
   36
   [{:head-index 14,
     :gender :neutral,
     :position [5 5],
     :mention-id 36,
     :end-index 22,
     :coref-cluster-id 36,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 5,
     :mention-span
     "the reforms and spending changes which might be needed",
     :number :plural,
     :start-index 13}],
   68
   [{:head-index 10,
     :gender :neutral,
     :position [9 3],
     :mention-id 68,
     :end-index 11,
     :coref-cluster-id 68,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 9,
     :mention-span "child benefit",
     :number :singular,
     :start-index 9}],
   5
   [{:head-index 14,
     :gender :female,
     :position [1 4],
     :mention-id 5,
     :end-index 15,
     :coref-cluster-id 5,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 1,
     :mention-span "her",
     :number :singular,
     :start-index 14}
    {:head-index 9,
     :gender :female,
     :position [2 4],
     :mention-id 12,
     :end-index 10,
     :coref-cluster-id 5,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 2,
     :mention-span "she",
     :number :singular,
     :start-index 9}
    {:head-index 1,
     :gender :female,
     :position [5 1],
     :mention-id 33,
     :end-index 2,
     :coref-cluster-id 5,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 5,
     :mention-span "She",
     :number :singular,
     :start-index 1}
    {:head-index 8,
     :gender :female,
     :position [5 3],
     :mention-id 35,
     :end-index 9,
     :coref-cluster-id 5,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 5,
     :mention-span "her",
     :number :singular,
     :start-index 8}
    {:head-index 1,
     :gender :female,
     :position [6 1],
     :mention-id 40,
     :end-index 2,
     :coref-cluster-id 5,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 6,
     :mention-span "She",
     :number :singular,
     :start-index 1}],
   101
   [{:head-index 37,
     :gender :unknown,
     :position [13 11],
     :mention-id 101,
     :end-index 38,
     :coref-cluster-id 101,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 13,
     :mention-span "20 years",
     :number :singular,
     :start-index 36}],
   133
   [{:head-index 2,
     :gender :neutral,
     :position [18 1],
     :mention-id 133,
     :end-index 3,
     :coref-cluster-id 133,
     :animacy :unknown,
     :mention-type :nominal,
     :sent-num 18,
     :mention-span "This",
     :number :singular,
     :start-index 2}
    {:head-index 7,
     :gender :neutral,
     :position [18 2],
     :mention-id 134,
     :end-index 11,
     :coref-cluster-id 133,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 18,
     :mention-span "a matter of social justice",
     :number :singular,
     :start-index 6}
    {:head-index 12,
     :gender :unknown,
     :position [18 3],
     :mention-id 137,
     :end-index 13,
     :coref-cluster-id 133,
     :animacy :inanimate,
     :mention-type :pronominal,
     :sent-num 18,
     :mention-span "it",
     :number :singular,
     :start-index 12}
    {:head-index 17,
     :gender :unknown,
     :position [18 4],
     :mention-id 138,
     :end-index 22,
     :coref-cluster-id 133,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 18,
     :mention-span "an economic imperative in a fast-changing world",
     :number :unknown,
     :start-index 15}],
   38
   [{:head-index 14,
     :gender :neutral,
     :position [5 6],
     :mention-id 38,
     :end-index 15,
     :coref-cluster-id 38,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 5,
     :mention-span "the reforms",
     :number :plural,
     :start-index 13}],
   70
   [{:head-index 4,
     :gender :unknown,
     :position [10 2],
     :mention-id 70,
     :end-index 7,
     :coref-cluster-id 70,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 10,
     :mention-span "extended paternity and maternity",
     :number :singular,
     :start-index 3}],
   102
   [{:head-index 5,
     :gender :neutral,
     :position [14 1],
     :mention-id 102,
     :end-index 10,
     :coref-cluster-id 102,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 14,
     :mention-span "our schools , colleges and universities",
     :number :plural,
     :start-index 4}
    {:head-index 5,
     :gender :neutral,
     :position [14 2],
     :mention-id 103,
     :end-index 6,
     :coref-cluster-id 102,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 14,
     :mention-span "our schools",
     :number :plural,
     :start-index 4}
    {:head-index 7,
     :gender :neutral,
     :position [14 4],
     :mention-id 105,
     :end-index 8,
     :coref-cluster-id 102,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 14,
     :mention-span "colleges",
     :number :plural,
     :start-index 7}
    {:head-index 9,
     :gender :neutral,
     :position [14 5],
     :mention-id 106,
     :end-index 10,
     :coref-cluster-id 102,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 14,
     :mention-span "universities",
     :number :plural,
     :start-index 9}
    {:head-index 10,
     :gender :neutral,
     :position [16 3],
     :mention-id 123,
     :end-index 11,
     :coref-cluster-id 102,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 16,
     :mention-span "universities",
     :number :plural,
     :start-index 10}],
   7
   [{:head-index 4,
     :gender :neutral,
     :position [2 2],
     :mention-id 7,
     :end-index 5,
     :coref-cluster-id 7,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 2,
     :mention-span "Labor Party",
     :number :singular,
     :start-index 3}],
   39
   [{:head-index 17,
     :gender :unknown,
     :position [5 7],
     :mention-id 39,
     :end-index 18,
     :coref-cluster-id 39,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 5,
     :mention-span "spending changes",
     :number :plural,
     :start-index 16}],
   71
   [{:head-index 3,
     :gender :unknown,
     :position [11 3],
     :mention-id 71,
     :end-index 4,
     :coref-cluster-id 71,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 11,
     :mention-span "600,000",
     :number :singular,
     :start-index 3}],
   8
   [{:head-index 7,
     :gender :neutral,
     :position [2 3],
     :mention-id 8,
     :end-index 8,
     :coref-cluster-id 8,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 2,
     :mention-span "Inverness",
     :number :singular,
     :start-index 7}],
   9
   [{:head-index 14,
     :gender :neutral,
     :position [2 6],
     :mention-id 9,
     :end-index 15,
     :coref-cluster-id 9,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 2,
     :mention-span "Scotland",
     :number :singular,
     :start-index 14}
    {:head-index 9,
     :gender :neutral,
     :position [3 4],
     :mention-id 19,
     :end-index 10,
     :coref-cluster-id 9,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 3,
     :mention-span "Scotland",
     :number :singular,
     :start-index 9}
    {:head-index 32,
     :gender :neutral,
     :position [20 13],
     :mention-id 145,
     :end-index 33,
     :coref-cluster-id 9,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 20,
     :mention-span "Scotland",
     :number :singular,
     :start-index 32}],
   41
   [{:head-index 5,
     :gender :neutral,
     :position [6 2],
     :mention-id 41,
     :end-index 22,
     :coref-cluster-id 41,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 6,
     :mention-span
     "Labor will enter the next Holyrood election with a pledge to boost education - including lifelong learning",
     :number :singular,
     :start-index 5}],
   73
   [{:head-index 4,
     :gender :neutral,
     :position [11 2],
     :mention-id 73,
     :end-index 5,
     :coref-cluster-id 73,
     :animacy :animate,
     :mention-type :nominal,
     :sent-num 11,
     :mention-span "600,000 children",
     :number :plural,
     :start-index 3}],
   10
   [{:head-index 5,
     :gender :neutral,
     :position [2 1],
     :mention-id 10,
     :end-index 8,
     :coref-cluster-id 10,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 2,
     :mention-span "the Labor Party conference in Inverness",
     :number :singular,
     :start-index 2}],
   43
   [{:head-index 11,
     :gender :neutral,
     :position [6 3],
     :mention-id 43,
     :end-index 12,
     :coref-cluster-id 43,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 6,
     :mention-span "the next Holyrood election",
     :number :singular,
     :start-index 8}],
   75
   [{:head-index 2,
     :gender :neutral,
     :position [12 1],
     :mention-id 75,
     :end-index 3,
     :coref-cluster-id 75,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 12,
     :mention-span "The government",
     :number :singular,
     :start-index 1}
    {:head-index 7,
     :gender :unknown,
     :position [12 3],
     :mention-id 78,
     :end-index 8,
     :coref-cluster-id 75,
     :animacy :inanimate,
     :mention-type :pronominal,
     :sent-num 12,
     :mention-span "its",
     :number :singular,
     :start-index 7}
    {:head-index 21,
     :gender :unknown,
     :position [12 6],
     :mention-id 83,
     :end-index 22,
     :coref-cluster-id 75,
     :animacy :inanimate,
     :mention-type :pronominal,
     :sent-num 12,
     :mention-span "its",
     :number :singular,
     :start-index 21}],
   107
   [{:head-index 14,
     :gender :unknown,
     :position [14 6],
     :mention-id 107,
     :end-index 18,
     :coref-cluster-id 107,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 14,
     :mention-span "the best in the world",
     :number :unknown,
     :start-index 13}],
   44
   [{:head-index 14,
     :gender :neutral,
     :position [6 4],
     :mention-id 44,
     :end-index 18,
     :coref-cluster-id 44,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 6,
     :mention-span "a pledge to boost education",
     :number :singular,
     :start-index 13}],
   76
   [{:head-index 8,
     :gender :neutral,
     :position [12 2],
     :mention-id 76,
     :end-index 12,
     :coref-cluster-id 76,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 12,
     :mention-span "its funding of further education",
     :number :singular,
     :start-index 7}],
   140
   [{:head-index 21,
     :gender :neutral,
     :position [18 5],
     :mention-id 140,
     :end-index 22,
     :coref-cluster-id 140,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 18,
     :mention-span "a fast-changing world",
     :number :singular,
     :start-index 19}],
   13
   [{:head-index 12,
     :gender :neutral,
     :position [2 5],
     :mention-id 13,
     :end-index 24,
     :coref-cluster-id 13,
     :animacy :animate,
     :mention-type :nominal,
     :sent-num 2,
     :mention-span
     "delegates that Scotland should boost education as `` an economic imperative ''",
     :number :plural,
     :start-index 12}
    {:head-index 7,
     :gender :neutral,
     :position [8 2],
     :mention-id 58,
     :end-index 8,
     :coref-cluster-id 13,
     :animacy :animate,
     :mention-type :nominal,
     :sent-num 8,
     :mention-span "delegates",
     :number :plural,
     :start-index 7}
    {:head-index 2,
     :gender :unknown,
     :position [9 1],
     :mention-id 66,
     :end-index 3,
     :coref-cluster-id 13,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 9,
     :mention-span "We",
     :number :plural,
     :start-index 2}
    {:head-index 1,
     :gender :unknown,
     :position [10 1],
     :mention-id 69,
     :end-index 2,
     :coref-cluster-id 13,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 10,
     :mention-span "We",
     :number :plural,
     :start-index 1}
    {:head-index 1,
     :gender :unknown,
     :position [11 1],
     :mention-id 72,
     :end-index 2,
     :coref-cluster-id 13,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 11,
     :mention-span "We",
     :number :plural,
     :start-index 1}],
   109
   [{:head-index 17,
     :gender :neutral,
     :position [14 7],
     :mention-id 109,
     :end-index 18,
     :coref-cluster-id 109,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 14,
     :mention-span "the world",
     :number :singular,
     :start-index 16}],
   141
   [{:head-index 4,
     :gender :unknown,
     :position [19 1],
     :mention-id 141,
     :end-index 9,
     :coref-cluster-id 141,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 19,
     :mention-span "others talk of an oil boom",
     :number :singular,
     :start-index 3}],
   47
   [{:head-index 8,
     :gender :neutral,
     :position [7 3],
     :mention-id 47,
     :end-index 9,
     :coref-cluster-id 47,
     :animacy :animate,
     :mention-type :proper,
     :sent-num 7,
     :mention-span "two-year-olds",
     :number :singular,
     :start-index 8}],
   111
   [{:head-index 22,
     :gender :neutral,
     :position [14 9],
     :mention-id 111,
     :end-index 25,
     :coref-cluster-id 111,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 14,
     :mention-span "length of time",
     :number :singular,
     :start-index 22}],
   143
   [{:head-index 8,
     :gender :neutral,
     :position [19 2],
     :mention-id 143,
     :end-index 9,
     :coref-cluster-id 143,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 19,
     :mention-span "an oil boom",
     :number :singular,
     :start-index 6}],
   16
   [{:head-index 22,
     :gender :unknown,
     :position [2 7],
     :mention-id 16,
     :end-index 23,
     :coref-cluster-id 16,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 2,
     :mention-span "an economic imperative",
     :number :unknown,
     :start-index 20}],
   80
   [{:head-index 17,
     :gender :neutral,
     :position [12 4],
     :mention-id 80,
     :end-index 31,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :nominal,
     :sent-num 12,
     :mention-span
     "former college principals who say that its free university tuition fees policy is costing college places",
     :number :plural,
     :start-index 15}
    {:head-index 10,
     :gender :unknown,
     :position [13 2],
     :mention-id 90,
     :end-index 11,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 13,
     :mention-span "we",
     :number :plural,
     :start-index 10}
    {:head-index 17,
     :gender :unknown,
     :position [13 5],
     :mention-id 93,
     :end-index 18,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 13,
     :mention-span "we",
     :number :plural,
     :start-index 17}
    {:head-index 4,
     :gender :unknown,
     :position [14 3],
     :mention-id 104,
     :end-index 5,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 14,
     :mention-span "our",
     :number :plural,
     :start-index 4}
    {:head-index 19,
     :gender :unknown,
     :position [14 8],
     :mention-id 110,
     :end-index 20,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 14,
     :mention-span "we",
     :number :plural,
     :start-index 19}
    {:head-index 2,
     :gender :unknown,
     :position [15 1],
     :mention-id 114,
     :end-index 3,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 15,
     :mention-span "we",
     :number :plural,
     :start-index 2}
    {:head-index 5,
     :gender :unknown,
     :position [17 2],
     :mention-id 127,
     :end-index 6,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 17,
     :mention-span "we",
     :number :plural,
     :start-index 5}
    {:head-index 1,
     :gender :unknown,
     :position [20 2],
     :mention-id 147,
     :end-index 2,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 20,
     :mention-span "Our",
     :number :plural,
     :start-index 1}
    {:head-index 7,
     :gender :unknown,
     :position [20 4],
     :mention-id 149,
     :end-index 8,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 20,
     :mention-span "our",
     :number :plural,
     :start-index 7}
    {:head-index 11,
     :gender :unknown,
     :position [20 5],
     :mention-id 150,
     :end-index 12,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 20,
     :mention-span "we",
     :number :plural,
     :start-index 11}
    {:head-index 29,
     :gender :unknown,
     :position [20 12],
     :mention-id 158,
     :end-index 30,
     :coref-cluster-id 80,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 20,
     :mention-span "we",
     :number :plural,
     :start-index 29}],
   144
   [{:head-index 26,
     :gender :unknown,
     :position [20 11],
     :mention-id 144,
     :end-index 27,
     :coref-cluster-id 144,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 20,
     :mention-span "a second",
     :number :singular,
     :start-index 25}],
   49
   [{:head-index 5,
     :gender :neutral,
     :position [7 1],
     :mention-id 49,
     :end-index 18,
     :coref-cluster-id 49,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 7,
     :mention-span
     "provision for all two-year-olds , rather than just those who are in care",
     :number :singular,
     :start-index 5}
    {:head-index 5,
     :gender :neutral,
     :position [7 2],
     :mention-id 50,
     :end-index 9,
     :coref-cluster-id 49,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 7,
     :mention-span "provision for all two-year-olds",
     :number :singular,
     :start-index 5}],
   113
   [{:head-index 24,
     :gender :neutral,
     :position [14 10],
     :mention-id 113,
     :end-index 25,
     :coref-cluster-id 113,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 14,
     :mention-span "time",
     :number :singular,
     :start-index 24}],
   82
   [{:head-index 26,
     :gender :neutral,
     :position [12 5],
     :mention-id 82,
     :end-index 27,
     :coref-cluster-id 82,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 12,
     :mention-span "its free university tuition fees policy",
     :number :singular,
     :start-index 21}],
   146
   [{:head-index 3,
     :gender :neutral,
     :position [20 1],
     :mention-id 146,
     :end-index 4,
     :coref-cluster-id 146,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 20,
     :mention-span "Our greatest resource",
     :number :singular,
     :start-index 1}],
   115
   [{:head-index 7,
     :gender :neutral,
     :position [15 2],
     :mention-id 115,
     :end-index 10,
     :coref-cluster-id 115,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 15,
     :mention-span "opportunity for some",
     :number :singular,
     :start-index 7}],
   20
   [{:head-index 4,
     :gender :male,
     :position [3 1],
     :mention-id 20,
     :end-index 5,
     :coref-cluster-id 20,
     :animacy :animate,
     :mention-type :proper,
     :sent-num 3,
     :mention-span "First Minister Alex Salmond",
     :number :singular,
     :start-index 1}],
   84
   [{:head-index 30,
     :gender :neutral,
     :position [12 7],
     :mention-id 84,
     :end-index 31,
     :coref-cluster-id 84,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 12,
     :mention-span "college places",
     :number :plural,
     :start-index 29}],
   148
   [{:head-index 8,
     :gender :neutral,
     :position [20 3],
     :mention-id 148,
     :end-index 9,
     :coref-cluster-id 148,
     :animacy :animate,
     :mention-type :nominal,
     :sent-num 20,
     :mention-span "our people",
     :number :plural,
     :start-index 7}
    {:head-index 15,
     :gender :neutral,
     :position [20 6],
     :mention-id 151,
     :end-index 16,
     :coref-cluster-id 148,
     :animacy :animate,
     :mention-type :nominal,
     :sent-num 20,
     :mention-span "people",
     :number :plural,
     :start-index 15}
    {:head-index 20,
     :gender :unknown,
     :position [20 9],
     :mention-id 154,
     :end-index 21,
     :coref-cluster-id 148,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 20,
     :mention-span "their",
     :number :plural,
     :start-index 20}],
   21
   [{:head-index 7,
     :gender :neutral,
     :position [3 3],
     :mention-id 21,
     :end-index 10,
     :coref-cluster-id 21,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 3,
     :mention-span "childcare in Scotland",
     :number :singular,
     :start-index 7}],
   53
   [{:head-index 13,
     :gender :neutral,
     :position [7 4],
     :mention-id 53,
     :end-index 18,
     :coref-cluster-id 53,
     :animacy :unknown,
     :mention-type :nominal,
     :sent-num 7,
     :mention-span "just those who are in care",
     :number :plural,
     :start-index 12}],
   117
   [{:head-index 9,
     :gender :neutral,
     :position [15 3],
     :mention-id 117,
     :end-index 10,
     :coref-cluster-id 117,
     :animacy :unknown,
     :mention-type :nominal,
     :sent-num 15,
     :mention-span "some",
     :number :plural,
     :start-index 9}],
   86
   [{:head-index 15,
     :gender :neutral,
     :position [13 4],
     :mention-id 86,
     :end-index 16,
     :coref-cluster-id 86,
     :animacy :unknown,
     :mention-type :proper,
     :sent-num 13,
     :mention-span "Scottish",
     :number :singular,
     :start-index 15}],
   23
   [{:head-index 16,
     :gender :unknown,
     :position [3 5],
     :mention-id 23,
     :end-index 20,
     :coref-cluster-id 23,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 3,
     :mention-span "the powers offered by independence",
     :number :plural,
     :start-index 15}
    {:head-index 9,
     :gender :unknown,
     :position [4 3],
     :mention-id 30,
     :end-index 10,
     :coref-cluster-id 23,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 4,
     :mention-span "the powers",
     :number :plural,
     :start-index 8}],
   87
   [{:head-index 27,
     :gender :unknown,
     :position [13 9],
     :mention-id 87,
     :end-index 28,
     :coref-cluster-id 87,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 13,
     :mention-span "one",
     :number :singular,
     :start-index 27}],
   119
   [{:head-index 14,
     :gender :neutral,
     :position [15 4],
     :mention-id 119,
     :end-index 15,
     :coref-cluster-id 119,
     :animacy :animate,
     :mention-type :nominal,
     :sent-num 15,
     :mention-span "others",
     :number :plural,
     :start-index 14}],
   88
   [{:head-index 36,
     :gender :unknown,
     :position [13 12],
     :mention-id 88,
     :end-index 37,
     :coref-cluster-id 88,
     :animacy :unknown,
     :mention-type :proper,
     :sent-num 13,
     :mention-span "20",
     :number :singular,
     :start-index 36}],
   120
   [{:head-index 3,
     :gender :unknown,
     :position [16 1],
     :mention-id 120,
     :end-index 11,
     :coref-cluster-id 120,
     :animacy :unknown,
     :mention-type :nominal,
     :sent-num 16,
     :mention-span
     "The savaging of the college system to fund universities",
     :number :singular,
     :start-index 2}
    {:head-index 14,
     :gender :male,
     :position [16 4],
     :mention-id 124,
     :end-index 15,
     :coref-cluster-id 120,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 16,
     :mention-span "a disgrace",
     :number :singular,
     :start-index 13}],
   152
   [{:head-index 17,
     :gender :male,
     :position [20 7],
     :mention-id 152,
     :end-index 22,
     :coref-cluster-id 152,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 20,
     :mention-span "the chance to fulfil their potential",
     :number :singular,
     :start-index 16}],
   153
   [{:head-index 21,
     :gender :neutral,
     :position [20 8],
     :mention-id 153,
     :end-index 22,
     :coref-cluster-id 153,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 20,
     :mention-span "their potential",
     :number :singular,
     :start-index 20}],
   122
   [{:head-index 7,
     :gender :neutral,
     :position [16 2],
     :mention-id 122,
     :end-index 8,
     :coref-cluster-id 122,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 16,
     :mention-span "the college system",
     :number :singular,
     :start-index 5}],
   27
   [{:head-index 3,
     :gender :male,
     :position [4 1],
     :mention-id 27,
     :end-index 4,
     :coref-cluster-id 27,
     :animacy :animate,
     :mention-type :proper,
     :sent-num 4,
     :mention-span "Ms Lamont",
     :number :singular,
     :start-index 2}
    {:head-index 2,
     :gender :male,
     :position [8 1],
     :mention-id 56,
     :end-index 3,
     :coref-cluster-id 27,
     :animacy :animate,
     :mention-type :proper,
     :sent-num 8,
     :mention-span "Ms Lamont",
     :number :singular,
     :start-index 1}
    {:head-index 2,
     :gender :male,
     :position [13 1],
     :mention-id 85,
     :end-index 3,
     :coref-cluster-id 27,
     :animacy :animate,
     :mention-type :proper,
     :sent-num 13,
     :mention-span "Ms Lamont",
     :number :singular,
     :start-index 1}
    {:head-index 2,
     :gender :unknown,
     :position [17 1],
     :mention-id 126,
     :end-index 3,
     :coref-cluster-id 27,
     :animacy :animate,
     :mention-type :pronominal,
     :sent-num 17,
     :mention-span "I",
     :number :singular,
     :start-index 2}],
   59
   [{:head-index 10,
     :gender :neutral,
     :position [8 3],
     :mention-id 59,
     :end-index 13,
     :coref-cluster-id 59,
     :animacy :inanimate,
     :mention-type :proper,
     :sent-num 8,
     :mention-span "Labor in government",
     :number :singular,
     :start-index 10}],
   91
   [{:head-index 16,
     :gender :neutral,
     :position [13 3],
     :mention-id 91,
     :end-index 38,
     :coref-cluster-id 91,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 13,
     :mention-span
     "the next Scottish election we will have plans not just to change education over one term but a vision which will look foard 20 years",
     :number :singular,
     :start-index 13}],
   28
   [{:head-index 7,
     :gender :neutral,
     :position [4 2],
     :mention-id 28,
     :end-index 12,
     :coref-cluster-id 28,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 4,
     :mention-span "the conference the powers already exist",
     :number :singular,
     :start-index 6}],
   156
   [{:head-index 28,
     :gender :neutral,
     :position [20 10],
     :mention-id 156,
     :end-index 33,
     :coref-cluster-id 156,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 20,
     :mention-span "a second education boom we need in Scotland",
     :number :singular,
     :start-index 25}],
   62
   [{:head-index 16,
     :gender :neutral,
     :position [8 4],
     :mention-id 62,
     :end-index 23,
     :coref-cluster-id 62,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 8,
     :mention-span
     "a childcare strategy within months of coming into office",
     :number :singular,
     :start-index 14}],
   94
   [{:head-index 20,
     :gender :neutral,
     :position [13 6],
     :mention-id 94,
     :end-index 21,
     :coref-cluster-id 94,
     :animacy :inanimate,
     :mention-type :nominal,
     :sent-num 13,
     :mention-span "plans",
     :number :plural,
     :start-index 20}]})

(def maps
  '[{:rank 1.5453390363595516,
   :index 1,
   :sentence-parts
   [{:token "At", :lemma "at", :ne :null, :pos :in}
    {:token "the", :lemma "the", :ne :null, :pos :dt}
    {:token "Labor", :lemma "Labor", :ne :organization, :pos :nnp}
    {:token "Party", :lemma "Party", :ne :organization, :pos :nnp}
    {:token "conference", :lemma "conference", :ne :null, :pos :nn}
    {:token "in", :lemma "in", :ne :null, :pos :in}
    {:token "Inverness", :lemma "Inverness", :ne :location, :pos :nnp}
    {:token ",", :lemma ",", :ne :null, :pos :point}
    {:token "she", :lemma "she", :ne :null, :pos :prp}
    {:token "will", :lemma "will", :ne :null, :pos :md}
    {:token "tell", :lemma "tell", :ne :null, :pos :vb}
    {:token "delegates", :lemma "delegate", :ne :null, :pos :nns}
    {:token "that", :lemma "that", :ne :null, :pos :wdt}
    {:token "Scotland", :lemma "Scotland", :ne :location, :pos :nnp}
    {:token "should", :lemma "should", :ne :null, :pos :md}
    {:token "boost", :lemma "boost", :ne :null, :pos :vb}
    {:token "education", :lemma "education", :ne :null, :pos :nn}
    {:token "as", :lemma "as", :ne :null, :pos :in}
    {:token "``", :lemma "``", :ne :null, :pos :null}
    {:token "an", :lemma "a", :ne :null, :pos :dt}
    {:token "economic", :lemma "economic", :ne :null, :pos :jj}
    {:token "imperative", :lemma "imperative", :ne :null, :pos :jj}
    {:token "''", :lemma "''", :ne :null, :pos :null}
    {:token ".", :lemma ".", :ne :null, :pos :point}],
   :sentence
   "At the Labour Party conference in Inverness, she will tell delegates that\nScotland should boost education as \"an economic imperative\".",
   :tokens
   ("At"
    "the"
    "Labor"
    "Party"
    "conference"
    "in"
    "Inverness"
    ","
    "she"
    "will"
    "tell"
    "delegates"
    "that"
    "Scotland"
    "should"
    "boost"
    "education"
    "as"
    "``"
    "an"
    "economic"
    "imperative"
    "''"
    "."),
   :lemmas
   ("at"
    "the"
    "Labor"
    "Party"
    "conference"
    "in"
    "Inverness"
    ","
    "she"
    "will"
    "tell"
    "delegate"
    "that"
    "Scotland"
    "should"
    "boost"
    "education"
    "as"
    "``"
    "a"
    "economic"
    "imperative"
    "''"
    "."),
   :nes
   (:null
    :null
    :organization
    :organization
    :null
    :null
    :location
    :null
    :null
    :null
    :null
    :null
    :null
    :location
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null),
   :pos
   (:in
    :dt
    :nnp
    :nnp
    :nn
    :in
    :nnp
    :point
    :prp
    :md
    :vb
    :nns
    :wdt
    :nnp
    :md
    :vb
    :nn
    :in
    :null
    :dt
    :jj
    :jj
    :null
    :point)}
  {:rank 1.2782306417412275,
   :index 7,
   :sentence-parts
   [{:token "Ms", :lemma "Ms", :ne :person, :pos :nnp}
    {:token "Lamont", :lemma "Lamont", :ne :person, :pos :nnp}
    {:token "is", :lemma "be", :ne :null, :pos :vbz}
    {:token "expected", :lemma "expect", :ne :null, :pos :vbn}
    {:token "to", :lemma "to", :ne :null, :pos :to}
    {:token "tell", :lemma "tell", :ne :null, :pos :vb}
    {:token "delegates", :lemma "delegate", :ne :null, :pos :nns}
    {:token ":", :lemma ":", :ne :null, :pos :null}
    {:token "``", :lemma "``", :ne :null, :pos :null}
    {:token "Labor", :lemma "Labor", :ne :null, :pos :nnp}
    {:token "in", :lemma "in", :ne :null, :pos :in}
    {:token "government", :lemma "government", :ne :null, :pos :nn}
    {:token "has", :lemma "have", :ne :null, :pos :vbz}
    {:token "a", :lemma "a", :ne :null, :pos :dt}
    {:token "childcare", :lemma "childcare", :ne :null, :pos :nn}
    {:token "strategy", :lemma "strategy", :ne :null, :pos :nn}
    {:token "within", :lemma "within", :ne :null, :pos :in}
    {:token "months", :lemma "month", :ne :null, :pos :nns}
    {:token "of", :lemma "of", :ne :null, :pos :in}
    {:token "coming", :lemma "come", :ne :null, :pos :vbg}
    {:token "into", :lemma "into", :ne :null, :pos :in}
    {:token "office", :lemma "office", :ne :null, :pos :nn}
    {:token ".", :lemma ".", :ne :null, :pos :point}],
   :sentence
   "Ms Lamont is expected to tell delegates: \"Labour in government has a childcare\nstrategy within months of coming into office.",
   :tokens
   ("Ms"
    "Lamont"
    "is"
    "expected"
    "to"
    "tell"
    "delegates"
    ":"
    "``"
    "Labor"
    "in"
    "government"
    "has"
    "a"
    "childcare"
    "strategy"
    "within"
    "months"
    "of"
    "coming"
    "into"
    "office"
    "."),
   :lemmas
   ("Ms"
    "Lamont"
    "be"
    "expect"
    "to"
    "tell"
    "delegate"
    ":"
    "``"
    "Labor"
    "in"
    "government"
    "have"
    "a"
    "childcare"
    "strategy"
    "within"
    "month"
    "of"
    "come"
    "into"
    "office"
    "."),
   :nes
   (:person
    :person
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null),
   :pos
   (:nnp
    :nnp
    :vbz
    :vbn
    :to
    :vb
    :nns
    :null
    :null
    :nnp
    :in
    :nn
    :vbz
    :dt
    :nn
    :nn
    :in
    :nns
    :in
    :vbg
    :in
    :nn
    :point)}
  {:rank 1.3132999735431468,
   :index 12,
   :sentence-parts
   [{:token "Ms", :lemma "Ms", :ne :person, :pos :nnp}
    {:token "Lamont", :lemma "Lamont", :ne :person, :pos :nnp}
    {:token "is", :lemma "be", :ne :null, :pos :vbz}
    {:token "expected", :lemma "expect", :ne :null, :pos :vbn}
    {:token "to", :lemma "to", :ne :null, :pos :to}
    {:token "say", :lemma "say", :ne :null, :pos :vb}
    {:token ":", :lemma ":", :ne :null, :pos :null}
    {:token "``", :lemma "``", :ne :null, :pos :null}
    {:token "When", :lemma "when", :ne :null, :pos :wrb}
    {:token "we", :lemma "we", :ne :null, :pos :prp}
    {:token "go", :lemma "go", :ne :null, :pos :vbp}
    {:token "into", :lemma "into", :ne :null, :pos :in}
    {:token "the", :lemma "the", :ne :null, :pos :dt}
    {:token "next", :lemma "next", :ne :null, :pos :jj}
    {:token "Scottish", :lemma "scottish", :ne :misc, :pos :jj}
    {:token "election", :lemma "election", :ne :null, :pos :nn}
    {:token "we", :lemma "we", :ne :null, :pos :prp}
    {:token "will", :lemma "will", :ne :null, :pos :md}
    {:token "have", :lemma "have", :ne :null, :pos :vb}
    {:token "plans", :lemma "plan", :ne :null, :pos :nns}
    {:token "not", :lemma "not", :ne :null, :pos :rb}
    {:token "just", :lemma "just", :ne :null, :pos :rb}
    {:token "to", :lemma "to", :ne :null, :pos :to}
    {:token "change", :lemma "change", :ne :null, :pos :vb}
    {:token "education", :lemma "education", :ne :null, :pos :nn}
    {:token "over", :lemma "over", :ne :null, :pos :in}
    {:token "one", :lemma "one", :ne :null, :pos :cd}
    {:token "term", :lemma "term", :ne :null, :pos :nn}
    {:token "but", :lemma "but", :ne :null, :pos :cc}
    {:token "a", :lemma "a", :ne :null, :pos :dt}
    {:token "vision", :lemma "vision", :ne :null, :pos :nn}
    {:token "which", :lemma "which", :ne :null, :pos :wdt}
    {:token "will", :lemma "will", :ne :null, :pos :md}
    {:token "look", :lemma "look", :ne :null, :pos :vb}
    {:token "foard", :lemma "foard", :ne :null, :pos :nn}
    {:token "20", :lemma "20", :ne :null, :pos :cd}
    {:token "years", :lemma "year", :ne :null, :pos :nns}
    {:token ".", :lemma ".", :ne :null, :pos :point}],
   :sentence
   "Ms Lamont is expected to say: \"When we go into the next Scottish election we\nwill have plans not just to change education over one term but a vision\nwhich will look foard 20 years.",
   :tokens
   ("Ms"
    "Lamont"
    "is"
    "expected"
    "to"
    "say"
    ":"
    "``"
    "When"
    "we"
    "go"
    "into"
    "the"
    "next"
    "Scottish"
    "election"
    "we"
    "will"
    "have"
    "plans"
    "not"
    "just"
    "to"
    "change"
    "education"
    "over"
    "one"
    "term"
    "but"
    "a"
    "vision"
    "which"
    "will"
    "look"
    "foard"
    "20"
    "years"
    "."),
   :lemmas
   ("Ms"
    "Lamont"
    "be"
    "expect"
    "to"
    "say"
    ":"
    "``"
    "when"
    "we"
    "go"
    "into"
    "the"
    "next"
    "scottish"
    "election"
    "we"
    "will"
    "have"
    "plan"
    "not"
    "just"
    "to"
    "change"
    "education"
    "over"
    "one"
    "term"
    "but"
    "a"
    "vision"
    "which"
    "will"
    "look"
    "foard"
    "20"
    "year"
    "."),
   :nes
   (:person
    :person
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :misc
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null),
   :pos
   (:nnp
    :nnp
    :vbz
    :vbn
    :to
    :vb
    :null
    :null
    :wrb
    :prp
    :vbp
    :in
    :dt
    :jj
    :jj
    :nn
    :prp
    :md
    :vb
    :nns
    :rb
    :rb
    :to
    :vb
    :nn
    :in
    :cd
    :nn
    :cc
    :dt
    :nn
    :wdt
    :md
    :vb
    :nn
    :cd
    :nns
    :point)}
  {:rank 1.0496832512591407,
   :index 13,
   :sentence-parts
   [{:token "``", :lemma "``", :ne :null, :pos :null}
    {:token "Because", :lemma "because", :ne :null, :pos :in}
    {:token "if", :lemma "if", :ne :null, :pos :in}
    {:token "our", :lemma "we", :ne :null, :pos :prp$}
    {:token "schools", :lemma "school", :ne :null, :pos :nns}
    {:token ",", :lemma ",", :ne :null, :pos :point}
    {:token "colleges", :lemma "college", :ne :null, :pos :nns}
    {:token "and", :lemma "and", :ne :null, :pos :cc}
    {:token "universities", :lemma "university", :ne :null, :pos :nns}
    {:token "are", :lemma "be", :ne :null, :pos :vbp}
    {:token "to", :lemma "to", :ne :null, :pos :to}
    {:token "be", :lemma "be", :ne :null, :pos :vb}
    {:token "the", :lemma "the", :ne :null, :pos :dt}
    {:token "best", :lemma "best", :ne :null, :pos :jjs}
    {:token "in", :lemma "in", :ne :null, :pos :in}
    {:token "the", :lemma "the", :ne :null, :pos :dt}
    {:token "world", :lemma "world", :ne :null, :pos :nn}
    {:token ",", :lemma ",", :ne :null, :pos :point}
    {:token "we", :lemma "we", :ne :null, :pos :prp}
    {:token "need", :lemma "need", :ne :null, :pos :vbp}
    {:token "that", :lemma "that", :ne :null, :pos :in}
    {:token "length", :lemma "length", :ne :null, :pos :nn}
    {:token "of", :lemma "of", :ne :null, :pos :in}
    {:token "time", :lemma "time", :ne :null, :pos :nn}
    {:token ".", :lemma ".", :ne :null, :pos :point}],
   :sentence
   "\"Because if our schools, colleges and universities are to be the best in the\nworld, we need that length of time.",
   :tokens
   ("``"
    "Because"
    "if"
    "our"
    "schools"
    ","
    "colleges"
    "and"
    "universities"
    "are"
    "to"
    "be"
    "the"
    "best"
    "in"
    "the"
    "world"
    ","
    "we"
    "need"
    "that"
    "length"
    "of"
    "time"
    "."),
   :lemmas
   ("``"
    "because"
    "if"
    "we"
    "school"
    ","
    "college"
    "and"
    "university"
    "be"
    "to"
    "be"
    "the"
    "best"
    "in"
    "the"
    "world"
    ","
    "we"
    "need"
    "that"
    "length"
    "of"
    "time"
    "."),
   :nes
   (:null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null),
   :pos
   (:null
    :in
    :in
    :prp$
    :nns
    :point
    :nns
    :cc
    :nns
    :vbp
    :to
    :vb
    :dt
    :jjs
    :in
    :dt
    :nn
    :point
    :prp
    :vbp
    :in
    :nn
    :in
    :nn
    :point)}
  {:rank 1.266603548042542,
   :index 16,
   :sentence-parts
   [{:token "``", :lemma "``", :ne :null, :pos :null}
    {:token "I", :lemma "I", :ne :null, :pos :prp}
    {:token "want", :lemma "want", :ne :null, :pos :vbp}
    {:token "and", :lemma "and", :ne :null, :pos :cc}
    {:token "we", :lemma "we", :ne :null, :pos :prp}
    {:token "need", :lemma "need", :ne :null, :pos :vbp}
    {:token "a", :lemma "a", :ne :null, :pos :dt}
    {:token "Scotland", :lemma "Scotland", :ne :location, :pos :nnp}
    {:token "which", :lemma "which", :ne :null, :pos :wdt}
    {:token "does", :lemma "do", :ne :null, :pos :vbz}
    {:token "education", :lemma "education", :ne :null, :pos :nn}
    {:token "for", :lemma "for", :ne :null, :pos :in}
    {:token "all", :lemma "all", :ne :null, :pos :dt}
    {:token ".", :lemma ".", :ne :null, :pos :point}],
   :sentence
   "\"I want and we need a Scotland which does education for all.",
   :tokens
   ("``"
    "I"
    "want"
    "and"
    "we"
    "need"
    "a"
    "Scotland"
    "which"
    "does"
    "education"
    "for"
    "all"
    "."),
   :lemmas
   ("``"
    "I"
    "want"
    "and"
    "we"
    "need"
    "a"
    "Scotland"
    "which"
    "do"
    "education"
    "for"
    "all"
    "."),
   :nes
   (:null
    :null
    :null
    :null
    :null
    :null
    :null
    :location
    :null
    :null
    :null
    :null
    :null
    :null),
   :pos
   (:null
    :prp
    :vbp
    :cc
    :prp
    :vbp
    :dt
    :nnp
    :wdt
    :vbz
    :nn
    :in
    :dt
    :point)}
  {:rank 1.062913122662431,
   :index 19,
   :sentence-parts
   [{:token "Our", :lemma "we", :ne :null, :pos :prp$}
    {:token "greatest", :lemma "greatest", :ne :null, :pos :jjs}
    {:token "resource", :lemma "resource", :ne :null, :pos :nn}
    {:token "will", :lemma "will", :ne :null, :pos :md}
    {:token "always", :lemma "always", :ne :null, :pos :rb}
    {:token "be", :lemma "be", :ne :null, :pos :vb}
    {:token "our", :lemma "we", :ne :null, :pos :prp$}
    {:token "people", :lemma "people", :ne :null, :pos :nns}
    {:token "and", :lemma "and", :ne :null, :pos :cc}
    {:token "if", :lemma "if", :ne :null, :pos :in}
    {:token "we", :lemma "we", :ne :null, :pos :prp}
    {:token "are", :lemma "be", :ne :null, :pos :vbp}
    {:token "to", :lemma "to", :ne :null, :pos :to}
    {:token "give", :lemma "give", :ne :null, :pos :vb}
    {:token "people", :lemma "people", :ne :null, :pos :nns}
    {:token "the", :lemma "the", :ne :null, :pos :dt}
    {:token "chance", :lemma "chance", :ne :null, :pos :nn}
    {:token "to", :lemma "to", :ne :null, :pos :to}
    {:token "fulfil", :lemma "fulfil", :ne :null, :pos :vb}
    {:token "their", :lemma "they", :ne :null, :pos :prp$}
    {:token "potential", :lemma "potential", :ne :null, :pos :nn}
    {:token ",", :lemma ",", :ne :null, :pos :point}
    {:token "it", :lemma "it", :ne :null, :pos :prp}
    {:token "is", :lemma "be", :ne :null, :pos :vbz}
    {:token "a", :lemma "a", :ne :null, :pos :dt}
    {:token "second", :lemma "second", :ne :null, :pos :jj}
    {:token "education", :lemma "education", :ne :null, :pos :nn}
    {:token "boom", :lemma "boom", :ne :null, :pos :nn}
    {:token "we", :lemma "we", :ne :null, :pos :prp}
    {:token "need", :lemma "need", :ne :null, :pos :vbp}
    {:token "in", :lemma "in", :ne :null, :pos :in}
    {:token "Scotland", :lemma "Scotland", :ne :location, :pos :nnp}
    {:token ".", :lemma ".", :ne :null, :pos :point}
    {:token "''", :lemma "''", :ne :null, :pos :null}],
   :sentence
   "Our greatest resource will always be our\npeople and if we are to give people the chance to fulfil their potential, it\nis a second education boom we need in Scotland.\"",
   :tokens
   ("Our"
    "greatest"
    "resource"
    "will"
    "always"
    "be"
    "our"
    "people"
    "and"
    "if"
    "we"
    "are"
    "to"
    "give"
    "people"
    "the"
    "chance"
    "to"
    "fulfil"
    "their"
    "potential"
    ","
    "it"
    "is"
    "a"
    "second"
    "education"
    "boom"
    "we"
    "need"
    "in"
    "Scotland"
    "."
    "''"),
   :lemmas
   ("we"
    "greatest"
    "resource"
    "will"
    "always"
    "be"
    "we"
    "people"
    "and"
    "if"
    "we"
    "be"
    "to"
    "give"
    "people"
    "the"
    "chance"
    "to"
    "fulfil"
    "they"
    "potential"
    ","
    "it"
    "be"
    "a"
    "second"
    "education"
    "boom"
    "we"
    "need"
    "in"
    "Scotland"
    "."
    "''"),
   :nes
   (:null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :null
    :location
    :null
    :null),
   :pos
   (:prp$
    :jjs
    :nn
    :md
    :rb
    :vb
    :prp$
    :nns
    :cc
    :in
    :prp
    :vbp
    :to
    :vb
    :nns
    :dt
    :nn
    :to
    :vb
    :prp$
    :nn
    :point
    :prp
    :vbz
    :dt
    :jj
    :nn
    :nn
    :prp
    :vbp
    :in
    :nnp
    :point
    :null)}
  ])
