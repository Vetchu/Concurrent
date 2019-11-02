// const readline = require('readline');
// const rl = readline.createInterface({
//     input: process.stdin,
//     output: process.stdout
// });

const fs = require('fs');

function Transaction(id, assignee, operation) {
    this.id = id;
    this.assignee = assignee;
    this.operations = operation;
}

function generateList(data) {
    return data.replace(/ /g, "")
        .split("\n")
        .filter(element => element.indexOf(")") !== -1)
        .map(element => {
            const [first, second] =
                    element.split(")"),
                id = first.split("(")[1],
                assignee = second.split(":=")[0],
                operators = second.replace(/[0-9]/g, '').split(":=")[1].split(/[\-+:/]/);
            return new Transaction(id, assignee, operators);
        });
}

const findThisInArray = function (element) {
    const [tocompare,property,...rest]=this;
    return element[property] === tocompare.toString();
}

function generateDependencies(reasonlist) {
    const doesContain = (el1, el2) => el1.operations.includes(el2.assignee) || el2.operations.includes(el1.assignee);

    const dependent = reasonlist
        .map(element =>
            reasonlist
                .map(element2 => {
                    if (doesContain(element, element2)) return new Pair(element.id, element2.id)
                })
                .filter(el => el !== undefined))
        .flat();
    const independent = reasonlist
        .map(element =>
            reasonlist
                .map(element2 => {
                    if (!(doesContain(element, element2))) return new Pair(element.id, element2.id)
                })
                .filter(el => el !== undefined))
        .flat();

    return [dependent, independent];
}

function FoataClass(foataclass, next) {
    this.foataclass = foataclass;
    this.next = next;
}

FoataClass.prototype.toString = function () {
    console.log("lel")
    return "(" + this.foataclass + ")" //+ this.next;
};

function generateFoataNormalForm(transactions, word, independent) {
    const wordarray = word.split('');
    return wordarray.reduceRight((currentFoata, currentEl) => {
        // console.log(currentFoata)
        const sofar = currentFoata.foataclass;
        const independentwith = independent.filter(el => sofar.indexOf(el.second) !== -1 || sofar.indexOf(el.first));

        if (independentwith.find(findThisInArray, [currentEl, "second"]))
            return new FoataClass([currentEl], currentFoata);
        else {
            currentFoata.foataclass.push(currentEl);
            return currentFoata;
        }

        //      ? [currentEl] + [currentFoata]
        //      : currentFoata + currentEl
    }, new FoataClass([], null));
}

function Pair(first, second) {
    this.first = first;
    this.second = second;
}

Pair.prototype.toString = function () {
    return "(" + this.first + "," + this.second + ")";
};

function generateDickertGraph(foata) {
    console.log(foata)
    console.log(foata.foataclass)
    const newVar = foata.foataclass.concat(foata.next!==null? generateDickertGraph(foata.next):[]);
    console.log(JSON.stringify(newVar))

    return newVar;
}

async function defaultinit() {
    await fs.readFile('./alphabet', 'utf8', (err, data) => {
        if (err) throw err;
        const reasonlist = generateList(data);
        const [dependent, independent] = generateDependencies(reasonlist);
        console.log(`D = {${dependent.join(",")}}`);
        console.log(`I = {${independent.join(",")}}`);
        const word = data.split("\n")
            .filter(e => e.includes('w='))
            .slice(2);
        console.log(word)
        // console.log(reasonlist)
        const foata = generateFoataNormalForm(reasonlist, word, independent);
        console.log(JSON.stringify(foata, null, 4))

        const graph = generateDickertGraph(foata)
        console.log(graph)

        return reasonlist;
    });
    // await alphabet;
    // console.log(alphabet)
}

const compose = (...functions) => args => functions.reduceRight((arg, fn) => fn(arg), args);

defaultinit();
