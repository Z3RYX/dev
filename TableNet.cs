/*
Supposed output

 -- People --
| Name | Age |
|------|-----|
| Bob  |  24 |
| Dave |  60 |
 ------------

Count: 2
*/

var table = new(title: "People", titles: new string[] {"Name", "Age"});
table.Border.Update(x => x.Color = ConsoleColor.Green)
table.Columns["Age"].Alignment = Align.Right;
table.AddRow("Bob", 24);
table.AddRow("Dave", 60);

table.AddRow();
table[2]["Name"] = "Alex";
table[2]["Age"] = 15;

table += new Row("Thomas", 32);

table.InsertColumn("ID", 0);
foreach (var r = table) table[r.Index]["ID"] = r.Index + 1;

table.AddRow(table.Count + 1, "Frank", 21, "frankster.com");
table.Columns[3].Title = "Website";

var display = table.Display(ShowCount: true);
display.Update(x => x[0]["Name"] = "Mark");
display.Update(table);


var table1 = GetTable(1);
var table2 = GetTable(2);
var table3 = GetTable(3);

table1.AlignTables(out table2, out table3); // Aligns the width of table 2 and 3 based on table 1; wraps if wrapping is enabled, else it cuts off

/*

Parts of a table

Top Left
Top
Top ID Connector
Top Connector
Top Right
Title Left
Title ID Vert
Title Vert
Title Right
Title Left Connector
Title ID Bottom
Title ID Connector
Title Bottom
Title Connector
Title Right Connector
Left
ID Vert
Vert
Right
Left Connector
ID Connector
Mid
Mid Connector
Right Connector

*/