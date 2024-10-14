extension radius

@description('The Radius Application ID. Injected automatically by the rad CLI.')
param application string

resource subscriptionsWatcher 'Applications.Core/containers@2023-10-01-preview' = {
  name: 'subscriptions-watcher'
  properties: {
    application: application
    container: {
      image: 'ytimocin/subscriptions-watcher:latest'
      ports: {
        web: {
          containerPort: 3000
        }
      }
    }
    extensions: [
      {
        kind: 'daprSidecar'
        appId: 'subscriptions-watcher'
        appPort: 3000
      }
    ]
  }
}

resource newsletterAdmin 'Applications.Core/containers@2023-10-01-preview' = {
  name: 'newsletter-admin'
  properties: {
    application: application
    container: {
      image: 'ytimocin/newsletter-admin:latest'
      ports: {
        web: {
          containerPort: 3001
        }
      }
    }
    extensions: [
      {
        kind: 'daprSidecar'
        appId: 'newsletter-admin'
        appPort: 3001
      }
    ]
  }
}

resource newsletter 'Applications.Core/containers@2023-10-01-preview' = {
  name: 'newsletter'
  properties: {
    application: application
    container: {
      image: 'ytimocin/newsletter:latest'
      ports: {
        web: {
          containerPort: 8080
        }
      }
    }
    extensions: [
      {
        kind: 'daprSidecar'
        appId: 'newsletter'
        appPort: 8080
      }
    ]
  }
}

resource consumer 'Applications.Core/containers@2023-10-01-preview' = {
  name: 'consumer'
  properties: {
    application: application
    container: {
      image: 'ytimocin/consumer:latest'
      ports: {
        web: {
          containerPort: 8081
        }
      }
    }
    extensions: [
      {
        kind: 'daprSidecar'
        appId: 'consumer'
        appPort: 8081
      }
    ]
  }
}
