using Rich;
using Rich.Progress;
using static Rich.Console;

PrintF($"[color:red]This is {Colored("blue", new Color(0x0000FF))} text.");

var bar = new ProgressBar("Doing stuff", max: 100, timer: true, count: true); // [Doing stuff] ------.............. 27/100 - 00:00:03
bar.Start();
await Task.Run(() => {
    DoThing();
    bar.Current++;
});
bar.Complete();