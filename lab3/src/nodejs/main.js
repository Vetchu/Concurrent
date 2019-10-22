var Fork = function () {
    this.state = 0;
    return this;
}

async function myPromise(fork, time) {
    return new Promise( async (resolve, reject)=> {
        if (!fork.state) {
            // console.log("got it")
            fork.state = 1;
            resolve("done");
        } else {
            await setTimeout(async function () {
                resolve(myPromise(fork, time * 2));
            }, time);
        }
    })
}

Fork.prototype.acquire = async function (cb) {
    // console.log("waiting for")
    // await myPromise(this, 1);
   await myPromise(this,1)
}

Fork.prototype.release = async function() {
    this.state = 0;
}

var Philosopher =function (id, forks) {
    this.tmpTime = 0;
    this.id = id;
    this.forks = forks;
    this.f1 = forks[id % forks.length];
    this.f2 = forks[(id + 1) % forks.length];

    return this;
}

Philosopher.prototype.startCount = function()  {
    var hrTime = process.hrtime();
    this.tmpTime = hrTime[0] * 1000000000 + hrTime[1];
}

Philosopher.prototype.endCount = function () {
    var hrTime = process.hrtime();
    console.log(this.id + ";" + (hrTime[0] * 1000000000 + hrTime[1] - this.tmpTime));
    this.tmpTime = 0;
}

acquireBoth=async function(time,f1,f2){
    return new Promise(async function (resolve, reject) {
        if (!(f1.state || f2.state)) {
            f1.state = 1;
            f2.state = 1;
            resolve("done");
        } else {
            await setTimeout(async function () {
                resolve(acquireBoth(time * 2, f1, f2));
            }, time);
        }
    })
}

Philosopher.prototype.startNaive = async function (count) {
    var f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    for (let i = 0; i < count; i++) {
        this.startCount();
        await f1.acquire();
        await f2.acquire();
        this.endCount();
        await setTimeout(function () {
            f2.release();
            f1.release();
        }, 1000);
    }
}

Philosopher.prototype.startAsym = async function (count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;


    for (let i = 0; i < count; i++) {
        this.startCount();
        if (this.id % 2 === 0) {
            await f1.acquire();
            await f2.acquire();
        } else {
            await f2.acquire();
            await f1.acquire();
        }
        this.endCount();
        await setTimeout(function () {
            f2.release();
            f1.release();
        }, 1000);
    }
}

Philosopher.prototype.startConductor = async function (count,conductor) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    for (var i = 0; i < count; i++) {
        this.startCount();
        await acquireBoth(1,f1,f2)
        // console.log("acquired both")
        this.endCount();
        await setTimeout(function () {
            f2.release();
            f1.release();
        }, 1000);
    }
}

Philosopher.prototype.startBoth = async function (count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    for (let i = 0; i < count; i++) {
        this.startCount();
        await acquireBoth(1,f1,f2)
        // console.log("acquired both")
        this.endCount();
        await setTimeout(function () {
            f2.release();
            f1.release();
        }, 1000);
    }
}


var N = 5;
var forks = [];
var philosophers = []

for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}


// for (var i = 0; i < N-1; i++) {
//     philosophers.push(new Philosopher(i, forks));
//     philosophers[i].startNaive(10);
// }
// setTimeout(function () {
//     var i = 4
//     philosophers.push(new Philosopher(i, forks));
//     philosophers[i].startNaive(10);
// },1000)
//
// for (var i = 0; i < N; i++) {
//     philosophers.push(new Philosopher(i, forks));
//     philosophers[i].startAsym(10);
// }
// for (var i = 0; i < N; i++) {
//     philosophers.push(new Philosopher(i, forks));
//     philosophers[i].startBoth(10);
// }
for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
    var conductor=Fork();
    conductor.state=4;
    philosophers[i].startConductor(10,conductor);
}
