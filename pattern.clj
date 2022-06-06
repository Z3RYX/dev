PATTERN Specification
(it's only in clojure for the syntax highlighting)

PATTERN is an esolang that works with a tape of bits. And you need to use patterns to find specific bits on that tape.

The memory is an infinite* tape of bits                 ;*limit up to whoever implements the language
A memory location is found with a pattern, of which the interpreter jumps to the first occurence -> [0101] jumps to the 5th character in 001000101100
If a pattern can't be found, the execution will stop and an error be thrown
Empty patterns will error
Patterns can be of arbitrary length

[x]       -> First occurence of the pattern, x is an arbitrary amount of 0s and 1s
[[x]]     -> Second occurence of the pattern
[[[x]]]   -> Third occurence of the pattern, you get the point
[-x-]     -> Last occurence of the pattern (interprets as 1x if no 1 is present in the pattern, i.e. [-000-] would find 3 zeros behind the last 1 as if it were [-1000-])
[x][x]    -> Uses the bits between the two patterns as a pattern, the second pattern will always search from the end of the first pattern
[x]-[x]   -> Pattern chain: the next pattern will always search from the end of the pattern before it

Patterns can be mixed:
[[-01110-]]-[101] -> This will search for the first occurence of 101 after the second to last occurence of 01110

It comes with a few instructions:
<[]     This toggles the bit preceeding the pattern, if there is one
>[]     This toggles the bit succeeding the pattern, if there is one
![]     Does a call on the 8 bits starting at the front of the pattern
$[]     Does a call on the 8 bits following the pattern

Other instructions are called via patterns:
Instruction: 8 bits
Arguments: arbitrary

NOP => [00000000]
    Won't do anything, but will also throw no error if called

print for => [00000001]
    [XXXXXXXX]: amount of characters to print (error if 0)
    [XXXXXXXX]{1-255}: characters to print

print until => [00000010]
    [XXXXXXXX]: print until this pattern is encountered (starting at each character)
    [XXXXXXXX]+: characters to print

input for => [00000011]
    [XXXXXXXX]: amount of characters to read (error if 0)

input until => [00000100]
    [XXXXXXXX]: read until this pattern is encountered (starting at each character)

math => [00000101]
    [XXX]: math operation (0: add, 1: subtract, 2: multiply, 3: divide, 4: modulo)
    [XX]: amount of bits to use (0: 8, 1: 16, 2: 32, 3: 64)
    [X{8-64}]: first value
    [X{8-64}]: second value
    Stores the result in the first value

conditional jump => [00000110]
    [X]: value to compare to 0
    [XXXXXXXX]: pattern to jump to if the value is true
    [XXXXXXXX]: pattern to jump to if the value is false
    Searches for the pattern starting at the end of the last argument

jump code => [00000111]
    [XXXXXXXX]: jumps this many lines in the code (signed byte)
    
toggle bit => [00001000]
    [XXX]: length of the pattern
    [X{1-8}]: pattern after which the bit is toggled

call => [00001001]
    [XXXXXXXX]: function to call

goto code => [00001010]
    [XXXXXXXX XXXXXXXX]: line in the code to jump to, starting at 0 (empty lines and comments are ignored)



--------------
-= Examples =-
--------------

Cat program:
; read input until line feed
>[00000]
>[10]
>[10000]
>[-0-]
![0]

; output the input
>[00000]
>[000000]
![0]
