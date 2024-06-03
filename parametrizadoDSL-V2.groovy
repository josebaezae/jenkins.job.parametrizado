job('ejemplo-job-DSL-version-2') {
  description('job dsl de ejemplo para el curso de jenkins-02-jun-2024')
  scm {
      		git('https://github.com/josebaezae/jenkins.job.parametrizado.git', 'main') { node ->
        		node / gitConfigName('josebaeza')
        		node / gitConfigEmail('josebaezae@gmail.com')
      		}
    	}
  parameters {
   		stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job Booleano')
      		choiceParam('planeta', ['Mercurio', 'Venus', 'Tierrra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
      		booleanParam('agente', false)
    	}
  triggers {
    		cron('H/7 * * * *')
    	}
  steps {
    		shell("bash jobscript.sh")
    	}
  publishers {
      		mailer('josebaezae@gmail.com', true, true)
      		slackNotifier {
		  		notifyAborted(true)
		  		notifyEveryFailure(true)
		  		notifyNotBuilt(false)
		  		notifyUnstable(false)
				notifyBackToNormal(true)
			  	notifySuccess(false)
		  		notifyRepeatedFailure(false)
		  		startNotification(false)
		  		includeTestSummary(false)
		  		includeCustomMessage(false)
		  		customMessage(null)
		  		sendAs(null)
		  		commitInfoChoice('NONE')
		  	teamDomain(null)
		  	authToken(null)
        	}
    }
}
