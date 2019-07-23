# Yber shared libs
---

## Description
**Yber Jenkins libs** are  Jenkins libs that provide common actions\functions to perform simple tasks in pipelines.

These libraries are available to query in similar fashion on both declarative or scripted pipelines.

## Documentation

### Installation
 You can follow 2 ways to use these libs, static and dynamic.
* static https://jenkins.io/doc/book/pipeline/shared-libraries/
* dynamic (also mentioned in the previous link)
```groovy
library identifier: 'shared@master', retriever: modernSCM(
              [$class: 'GitSCMSource',
               remote: 'https://github.com/dzirg44/shared.git'])

// and after will call it as (for example)
def sendSlack = slack.createMessage()
// in your pipelines
```


### Libraries

* [`slack`](#slack)


<a name="slack"/>

### Slack Shared Libraries

Can help you to create pretty nice and informative`Slack` messages, instead of use templates or standard boring messages.

___Arguments___
* `createMessage` [] : Create template as map.

___Inputs___

| arguments | example | type | description |
| --------- | ------- | ---- | ----------- |
| n/a       | n/a     | n/a  | n/a         |

___Outputs___

| arguments     | example | type | description                                                |
| ------------- | ------- | ---- | ---------------------------------------------------------- |
| generated map | n/a     | map  | you can call nested elements as `sendSlack["attachments"]` |


