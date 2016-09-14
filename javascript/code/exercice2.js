
ticketManager = function(idliste,idform) {
    

    var iddivliste = idliste;
    var iddivform = idform;
    var tickets = []; 

    // generateur d'id interne au module (pour les tickets)
    var compteurId = 1;
    var getNextID = function() {
        return compteurId++;
    };
   
    var rafraichirDiv = function () {
          tickets.sort(function(t1, t2) {
              if (t1.urgence > t2.urgence) return 1;
              if (t1.urgence < t2.urgence) return -1;
              return 0;
          });
          var table = document.createElement("table");
          table.setAttribute("border", "2");
          var entete = document.createElement("tr");
          entete.innerHTML = "<th>typeTicket</th><th>description</th><th>urgence</th><th>date</th><th>informations complementaires</th>";
          table.appendChild(entete);
          for (var i = 0; i < tickets.length ; i++) {
              table.appendChild(tickets[i].toTableLine());
          }
          var divliste = document.getElementById(iddivliste);
          divliste.innerHTML = "";
          divliste.appendChild(table);
    };
 
    var updateForm = function(baliseSelect) {
         var choix = baliseSelect.value;
                 switch(choix) {
                        case "ticket":
                            document.getElementById("noMateriel").style.visibility = 'hidden';
                            document.getElementById("localisation").style.visibility = 'hidden';
                            document.getElementById("bugReport").style.visibility = 'hidden';
                            document.getElementById("nomLogiciel").style.visibility = 'hidden';
                            break;
                        case "ticketPanne":
                            document.getElementById("noMateriel").style.visibility = 'visible';
                            document.getElementById("localisation").style.visibility = 'visible';
                            document.getElementById("bugReport").style.visibility = 'hidden';
                            document.getElementById("nomLogiciel").style.visibility = 'hidden';
                            break;
                        case "ticketLogiciel":
                            document.getElementById("noMateriel").style.visibility = 'hidden';
                            document.getElementById("localisation").style.visibility = 'hidden';
                            document.getElementById("bugReport").style.visibility = 'visible';
                            document.getElementById("nomLogiciel").style.visibility = 'visible';
                            break;
                }
    };
     var moduleTicket = {
        "initManager" : function() {
         
             rafraichirDiv();
             var btCreer = document.getElementById("creer");
             btCreer.addEventListener('click', function() {
                 moduleTicket.ajouterTicket();
             });
             var listeselect = document.getElementById("typeTicket");
             listeselect.addEventListener('change', function() {
                 updateForm(this);
             });
             updateForm(listeselect);
        },

        "ajouterTicket" : function() {
            console.log("ajouter un ticket...");
            var form = document.getElementById(iddivform);
            var typeTicket = document.getElementById("typeTicket").value;
            var t = null;
            switch(typeTicket) {
                case "ticket":
                    t = new moduleTicket.Ticket(getNextID(),"", 0, "");
                    break;
                case "ticketPanne":
                    t = new moduleTicket.TicketPanne(getNextID(),"", 0, "", "", "");
                    break;
                case "ticketLogiciel":
                    t = new moduleTicket.TicketLogiciel(getNextID(),"", 0, "", "", "");
                    break;
            }
            // le ticket lit son contenu depuis les champs du formulaire
            t.fillFromForm(form);
            tickets.push(t);
            rafraichirDiv();
        },
        "augmenterUrgence" : function(bouton) {

            rafraichirDiv();
        },
        "reduireUrgence" : function(bouton) {

            rafraichirDiv();
        },
    };

    // declaration des classes "metier"
    moduleTicket.Ticket = function(id, description, urgence, date) {
        this.id = id;
        this.description = description;
        this.urgence = urgence;
        this.date = date;
    };

    moduleTicket.Ticket.prototype = Object.create(null);
    moduleTicket.Ticket.prototype.constructor = moduleTicket.Ticket;

    // cette fonction renvoie un element "<tr>" avec les champs
    // a l'interieur
    moduleTicket.Ticket.prototype.toTableLine = function() {
        var tr = document.createElement("tr");
        
        var cellule = document.createElement("td");
        cellule.appendChild(document.createTextNode(this.getTicketType()));
        tr.appendChild(cellule);

        cellule = document.createElement("td");
        cellule.appendChild(document.createTextNode(this.description));
        tr.appendChild(cellule);

        cellule = document.createElement("td");
        cellule.appendChild(document.createTextNode(this.urgence));
        var bt = document.createElement("button");
        bt.innerText = "+";
        var self = this;
        bt.addEventListener('click', function() {
            self.urgence++;
            rafraichirDiv();
        });
        cellule.appendChild(bt);
        bt = document.createElement("button");
        bt.innerText = "-";
        bt.addEventListener('click', function() {
            self.urgence = (self.urgence == 0)? 0 : self.urgence - 1;
            rafraichirDiv();
        });
        cellule.appendChild(bt);

        tr.appendChild(cellule);

        cellule = document.createElement("td");
        cellule.appendChild(document.createTextNode(this.date));
        tr.appendChild(cellule);

        return tr;
    };

    moduleTicket.Ticket.prototype.fillFromForm = function(formdiv) {
        this.description = document.getElementById("description").value;
        this.urgence = Number(document.getElementById("urgence").value);
        this.date = document.getElementById("date").value;
    };

    moduleTicket.Ticket.prototype.getTicketType = function() {
        return "ticket";
    };

    // TicketPanne
    moduleTicket.TicketPanne = function(id, description, urgence, date, noMateriel, localisation) {
        moduleTicket.Ticket.call(this, id, description, urgence, date);
        this.noMateriel = noMateriel;
        this.localisation = localisation;
    };

    moduleTicket.TicketPanne.prototype = Object.create(moduleTicket.Ticket.prototype);
    moduleTicket.TicketPanne.prototype.constructor = moduleTicket.TicketPanne;

    moduleTicket.TicketPanne.prototype.toTableLine = function() {
        var tr = moduleTicket.Ticket.prototype.toTableLine.call(this);
        var cellule = document.createElement("td");
        cellule.appendChild(document.createTextNode(
            "noMateriel = " + this.noMateriel +
            ", localisation = " + this.localisation));
        tr.appendChild(cellule);
        return tr;
    };

    moduleTicket.TicketPanne.prototype.fillFromForm = function(formdiv) {
        moduleTicket.Ticket.prototype.fillFromForm.call(this, formdiv);
        this.noMateriel = document.getElementById("noMateriel").value;
        this.localisation = document.getElementById("localisation").value;
    };

    moduleTicket.TicketPanne.prototype.getTicketType = function() {
        return "ticketPanne";
    };
    
    // ticketLogiciel
    moduleTicket.TicketLogiciel = function(id, description, urgence, date, bugReport, nomLogiciel) {
        moduleTicket.Ticket.call(this, id, description, urgence, date);
        this.bugReport = bugReport;
        this.nomLogiciel = nomLogiciel;
    };

    moduleTicket.TicketLogiciel.prototype = Object.create(moduleTicket.Ticket.prototype);
    moduleTicket.TicketLogiciel.prototype.constructor = moduleTicket.TicketLogiciel;

    moduleTicket.TicketLogiciel.prototype.toTableLine = function() {
        var tr = moduleTicket.Ticket.prototype.toTableLine.call(this);
        var cellule = document.createElement("td");
        cellule.appendChild(document.createTextNode(
            "bugReport = " + this.bugReport +
            ", nomLogiciel = " + this.nomLogiciel));
        tr.appendChild(cellule);
        return tr;

    };

    moduleTicket.TicketLogiciel.prototype.fillFromForm = function(formdiv) {
        moduleTicket.Ticket.prototype.fillFromForm.call(this, formdiv);
        this.bugReport = document.getElementById("bugReport").value;
        this.nomLogiciel = document.getElementById("nomLogiciel").value;
    };

    moduleTicket.TicketLogiciel.prototype.getTicketType = function() {
        return "ticketLogiciel";
    };
    


    console.log("initialisation du module ticketManager");

    return moduleTicket;
}("listeTicket", "ticketForm");


