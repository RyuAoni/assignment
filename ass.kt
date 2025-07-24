fun main(args: Array<String>) {
    var s = "()"
    printer(s)
    //true
    
    s = "({)}"
    printer(s)
    //false

    s = "()[]{}"
    printer(s)
    //true

    s = "(]"
    printer(s)
    //false

    s = "([)]"
    printer(s)
    //false

    s = "{[]}"
    printer(s)
    //true no
    
    s = "([])"
    printer(s)
    // true no
    
    s = "(]"
    printer(s)
    // false

    s = "()[]{}"
    printer(s)
    // true

    s = "([{}])"
    printer(s)
    // true
    
    s = "([)]"
    printer(s)
    // false 

    s = "{{()}}"
    printer(s)
    // true
    
    s = "((("
    printer(s)
    // false
    
    s = "(((("
    printer(s)
    // false

    s = "[()]{()}"
    printer(s)
    // true
    
    s = "())"
    printer(s)
    // false
    
    s = "())("
    printer(s)
    // false
    
    s = "(()}"
    printer(s)
    // false
    
    s = ")(}{"
    printer(s)
    // false
    
    s = "()}{"
    printer(s)
    // false
}

fun isValid(s: String): Boolean {
    //文字列sの長さ取得
    val stren = s.length
    
    //sの長さが奇数の時、falseを返す
    if (stren % 2 != 0) {
        return false
    }
    
    //sの文字が何かを番号で管理する
    var A = Array(stren+1) {0}
    
    //sを数字に変換
    for (i in 0..stren-1) {
        when (s[i]) {
            '(' -> A[i] = 1
            ')' -> A[i] = 2
            '{' -> A[i] = 3
            '}' -> A[i] = 4
            '[' -> A[i] = 5
            ']' -> A[i] = 6
            else -> return false
        }
    }
    
    //最後に値を返すための変数
    var n = true
    //出てきた括弧の回数を記録する配列。初期値は0
    var xy = Array(7) {0}
    
    //Aの中身を調べる
    for (i in 0..stren-1) {
        //もしA[i]が奇数だったら、開く括弧のため、xyのA[i]番目を増やす
        if(A[i].rem(2) == 1) {
            xy[A[i]] = xy[A[i]] + 1
        //そうでなかったら、a[i]が偶数だったら、閉じる括弧のため、xyのA[i]を減らす
        } else {
            xy[A[i]-1] = xy[A[i]-1] - 1
        }
        
        //ここで各開く括弧に対し、それらに続く括弧として正しくないものが発生した場合にfalseを返す
        when (A[i]) {
            1 -> {
                if (A[i+1] == 4 || A[i+1] == 6) {
                    n = false
                }
            }
            3 -> {
                if (A[i+1] == 2 || A[i+1] == 6) {
                    n = false
                }
            }
            5 -> {
                if (A[i+1] == 2 || A[i+1] == 4) {
                    n = false
                }
            }
        }
        
        //文字列の最後が来たときの処理
        if (i == stren-1) {
            //最後の文字が開く括弧だとfalseになる
            if (A[i].rem(2) == 1) {
                n = false
            }
        }
        
        //文字列の最初が来た時の処理
        if (i == 0) {
            //最初の文字が閉じる括弧だとfalseになる
            if (A[i].rem(2) == 0) {
                n = false
            }
        }
    }
    
    //yxは0に終息するため、0以外が無いか探す
    for (i in 0..6) {
        //0以外がある場合falseにする
        if(xy[i] != 0) {
            n = false
        }
    }
    
    return n
}

//表示
fun printer(s: String) {
    println(s)
    println(isValid(s))
    println()
}
