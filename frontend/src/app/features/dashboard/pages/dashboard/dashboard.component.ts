import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  userName = 'User';
  currentTime = '';
  greeting = '';

  modules = [
    { name: 'Projects',    icon: '<rect x="3" y="3" width="14" height="14" rx="2" stroke="currentColor" stroke-width="1.4"/><path d="M7 7h6M7 10h4M7 13h3" stroke="currentColor" stroke-width="1.4" stroke-linecap="round"/>' },
    { name: 'Directory',   icon: '<circle cx="10" cy="8" r="3.5" stroke="currentColor" stroke-width="1.4"/><path d="M3 18c0-3.314 3.134-6 7-6s7 2.686 7 6" stroke="currentColor" stroke-width="1.4" stroke-linecap="round"/>' },
    { name: 'Tenders',     icon: '<path d="M5 3h10a2 2 0 012 2v12a2 2 0 01-2 2H5a2 2 0 01-2-2V5a2 2 0 012-2z" stroke="currentColor" stroke-width="1.4"/><path d="M7 8h6M7 11h4" stroke="currentColor" stroke-width="1.4" stroke-linecap="round"/>' },
    { name: 'Analytics',   icon: '<path d="M3 15l4-5 4 3 4-6" stroke="currentColor" stroke-width="1.4" stroke-linecap="round" stroke-linejoin="round"/><circle cx="17" cy="7" r="1.5" fill="currentColor"/>' },
    { name: 'Messages',    icon: '<rect x="2" y="4" width="16" height="12" rx="2" stroke="currentColor" stroke-width="1.4"/><path d="M6 8h8M6 11h5" stroke="currentColor" stroke-width="1.4" stroke-linecap="round"/>' },
    { name: 'Connections', icon: '<circle cx="7" cy="10" r="2.5" stroke="currentColor" stroke-width="1.4"/><circle cx="14" cy="7" r="2" stroke="currentColor" stroke-width="1.4"/><path d="M9.5 10c1.5-.5 2.5-.5 4.5 0" stroke="currentColor" stroke-width="1.4" stroke-linecap="round"/>' },
  ];

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Temporary: read name from query param
    // Replace later with: this.authService.getCurrentUser().fullName
    this.route.queryParams.subscribe(params => {
      if (params['name']) this.userName = this.formatName(params['name']);
    });
    this.setGreeting();
    setInterval(() => this.setGreeting(), 60000);
  }

  formatName(raw: string): string {
    return raw.replace(/[._]/g, ' ')
      .split(' ')
      .map(w => w.charAt(0).toUpperCase() + w.slice(1))
      .join(' ');
  }

  setGreeting(): void {
    const h = new Date().getHours();
    this.greeting = h < 12 ? 'Good morning' : h < 17 ? 'Good afternoon' : 'Good evening';
    this.currentTime = new Date().toLocaleTimeString('en-IN', {
      hour: '2-digit', minute: '2-digit', hour12: true
    });
  }

  onSignOut(): void {
    // this.authService.logout();
    this.router.navigate(['/auth/signin']);
  }
}