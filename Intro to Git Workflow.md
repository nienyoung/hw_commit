# **Git workflow**

## As a source management system, Git inevitably involves multi person collaboration.Therefore, there must be a standardized workflow to make sure that you can effectively cooperate with each other, that the project can develop be in a good order

***

![](http://www.ruanyifeng.com/blogimg/asset/2015/bg2015080501.png)

## The picture shows how the workflow generally works. And now, let's introduce the git workflow

## **1. Establish a new branch**

### Each time a new function is developed, a separate branch should be created

### And you need to remember that

* Subtle bug can be modified in the original branch without having to build a new branch

* The name of new branches should be understandable

* At any time, the master branch is in a deployable state

```
# to get the master
$ git checkout master
$ git pull

# to establish a new branch mybranch
$ git checkout -b mybranch
```

## **2. Commit your branch**

### After finishing your code, you can commit you branch

```
# Save all changes (including new, modified and deleted)
$ git add --all

# View the files that have changed
$ git status

# List the results of diff
$ git commit --verbose
```

## **3. Submmit the imfomation of your commit**

### When your partners refer to the code, clear commit information will **allow them to quickly understand what you do, and why**

* The first line should be a summary

* And then you need to list the causes, main changes, and problems needing attention

```
Present-tense summary under 50 characters

* More information about commit (under 72 characters).
* More information about commit (under 72 characters).

```

## **4. Merge branch**

### Having finished your development, you can now merge *mybranch* into *master*

```
# Switch to master
$ git checkout master

# merge mybranch into master
$ git merge --no-ff branch_name
```

### By default, using `git merge mybranch`, Git will execute the "fast forward merge", directly merge *mybranch* into *master*, without retaining the development record of *mybranch*

### After using the `--no-ff` parameter, a new commit submission record will be added to *master*, **and the development record of *mybranch* is forced to be retained.** This will be convenient for future code analysis

![](http://upload-images.jianshu.io/upload_images/8133-114a9bef4172e72e.png?imageView2/2/w/1240/q/100)

## **5. Resolving branch merge conflicts**

### Sometimes merge operations don't go so smoothly. If you modify the same part of the same file in different branches, Git can not cleanly combine the two, only rely on hand to solve

```
$ git merge mybranch
Auto-merging index.html
CONFLICT (content): Merge conflict in index.html
Automatic merge failed; fix conflicts and then commit the result.
```

### Git has been merged, but not submitted, it will stop and wait for you to resolve the conflict. To see which files conflict when merging, you can consult with `git status`

```
$ git status
On branch master
You have unmerged paths.
  (fix conflicts and run "git commit")

Unmerged paths:
  (use "git add <file>..." to mark resolution)

    both modified:      index.html

no changes added to commit (use "git add" and/or "git commit -a")
```

### Any file that contains unresolved conflicts is listed in an **unmerged state**. Git adds standard conflict resolution tags to conflicting files that can be manually positioned and resolved by these conflicts. You can see that this file contains parts like this

```
<<<<<<< HEAD
<div id="footer">
      contact : orangehat@gmail.com
    </div>
=======
<div id="footer">
  please contact me at Aaaaaashu@gmail.com
</div>
>>>>>>> mybranch
```

### The only way to solve the conflict is to **choose one of the two or to integrate them by yourself**. For example, you can solve this problem by replacing it with the following

```
<div id="footer">
  please contact me at orangehat@gmail.com
</div>
```

### The solution of the adopted part of two branches, and also deleted the <<<<<<<, ======= and >>>>>>> these lines. After resolving all the conflicts in all the files, run `git add` to mark them as resolved states. Because once it is temporarily stored, it means that the conflict has been resolved

### Run `git status` again to confirm that all conflicts have been resolved

```
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   index.html
```

### If you feel satisfied and confirm that all conflicts have been resolved, that is to enter the temporary storage area, you can use `git commit` to complete the merge submission

***

## **Practice step by step**

Step      | git operation
-----------|--------------
Clone code| git clone
Create branch| git checkout -b mybranch
Development in branch| None
Review code| None
First test| None
Add code to branch| git add somefile
Submit code to branch| git commit -m "imformation"
Switch to master| git checkout master
Get the latest code from the far end| git pull origin master
Merge branch to master| git merge mybranch
Resolve conflicts when merging| ...
Second test| None
Get the latest code from the far end| git pull origin master
Push to master| git push origin master
Delete the local branch when all is done| git branch -d branch_name

> ## Referrence
> <http://www.ruanyifeng.com/blog/2015/08/git-use-process.html>
> <http://www.jianshu.com/p/50892fac6cbc>